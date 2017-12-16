package com.sda.controller;

import com.sda.model.*;
import com.sda.wyszukiwanie.WyszukiwanieBinarne;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ZarzadzanieBiblioteka {

    private static Biblioteka biblioteka;


    public ZarzadzanieBiblioteka(Biblioteka biblio){

        biblioteka = biblio;
    }

    private void dodajKsiazkeDoBiblioteki(Ksiazka ksiazka){

        biblioteka.dodajKsiazke(ksiazka);
    }

    public List<String> zwrocTytulyKsiazek() {
        return biblioteka.getListaKsiazek().stream().map(ksiazka -> ksiazka.getTytul()).collect(Collectors.toList());
    }

    public void wypozyczKsiazke(Ksiazka ksiazka, Uzytkownik uzytkownik){
        ksiazka.setDostepna(false);
        Wpis wpis = new Wpis(ksiazka, uzytkownik, LocalDate.now(), null);
        biblioteka.getRejestrWypozyczen().add(wpis);
    }

    public void zwrocKsiazke(Ksiazka ksiazka, Uzytkownik uzytkownik){
        Optional<Wpis> rejWyp = biblioteka.getRejestrWypozyczen().stream()
                .filter(wpis -> wpis.getKsiazka().equals(ksiazka) && wpis.getUzytkownik().equals(uzytkownik) && !wpis.getKsiazka()
                        .getDostepna()).findFirst();
        if ( rejWyp.isPresent()) {
            Wpis wpis = rejWyp.get();
            wpis.setDataZwrotu(LocalDate.now());
            wpis.getKsiazka().setDostepna(true);
            System.out.println("Ksiazka: {" + ksiazka.toString() + "} została pomyślnie zwrócona!");
        } else {
            System.out.println("WYSTĄPIL JAKIS BLAD!!!");
        }
    }

    public void wyswietlListeKsiazek() {

        for (Ksiazka ksiazka : biblioteka.getListaKsiazek()){
            System.out.println(ksiazka);
        }
    }

    public List<Ksiazka> wyszukajPoTytule(String tytul){
        WyszukiwanieBinarne wb = new WyszukiwanieBinarne(biblioteka);
        return wb.szukajTytul(tytul);

    }

    public List<Ksiazka> wyszukajPoAutorze(String imie, String nazwisko){
        WyszukiwanieBinarne wb = new WyszukiwanieBinarne(biblioteka);
        return wb.szukajAutora(imie, nazwisko);
    }

    public void  dodajUzytkownika(Uzytkownik uzytkownik){
        biblioteka.dodajUzytkownika(uzytkownik);
    }

    public void dodajUzytkownika(String imie, String nazwisko, Plec plec){
        // sprawdzenie czy uzytkownik juz istnieje?
        biblioteka.dodajUzytkownika(new Uzytkownik(imie, nazwisko, plec));
    }

    public void  wyswietlWypozyczoneKsiazki(){

        List<Ksiazka> wypozyczoneKsiazki = biblioteka.getRejestrWypozyczen().stream()
                .filter(wpis -> !wpis.getKsiazka().getDostepna()).map(Wpis::getKsiazka)
                .collect(Collectors.toList());

        System.out.println("AKTUALNIE WYPOZYCZONE KSIAZKI:");
        for (int i = 0; i < wypozyczoneKsiazki.size(); i++) {
            System.out.println(i+". " + wypozyczoneKsiazki.get(i));
        }

    }

    public void wyswietlWypozyczonePrzezUzytkownika(Uzytkownik uzytkownik){

        List<Wpis> rejestr = biblioteka.getRejestrWypozyczen();
        List<Ksiazka> wypozyczoneUzytkownika = rejestr.stream().filter(wpis -> wpis.getUzytkownik()
                .equals(uzytkownik) && !wpis.getKsiazka().getDostepna())
                .map(Wpis::getKsiazka).collect(Collectors.toList());

        System.out.println("AKTUALNIE WYPOZYCZONE KSIAZKI PRZEZ: " + uzytkownik.toString());
        for (int i = 0; i < wypozyczoneUzytkownika.size(); i++) {
            System.out.println(i+". " + wypozyczoneUzytkownika.get(i));
        }
    }
    public List<Wpis> getWypozyczoneKsiazki() {

        return biblioteka.getRejestrWypozyczen().stream()
                .filter(wpis -> !wpis.getKsiazka().getDostepna())
                .collect(Collectors.toList());
    }

    public List<Wpis> getWypozyczonePrzezUzytkownika(String imie, String nazwisko){

        List<Wpis> rejestr = biblioteka.getRejestrWypozyczen();

        List<Wpis> wypozyczone =  rejestr.stream().filter(wpis -> wpis.getUzytkownik().getImie().equalsIgnoreCase(imie) &&
                wpis.getUzytkownik().getNazwisko().equalsIgnoreCase(nazwisko) && !wpis.getKsiazka().getDostepna())
                .collect(Collectors.toList());

        if (wypozyczone.size() == 0 ) {
            return null;
        } else {
            return wypozyczone;
        }
    }


    public static void main(String[] args) {

        Biblioteka biblioteka = new Biblioteka();
        Backup b = new Backup();
        ZarzadzanieBiblioteka zb = new ZarzadzanieBiblioteka(biblioteka);

        try {
            b.odczytKsiazek(biblioteka);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        zb.dodajKsiazkeDoBiblioteki(new Ksiazka("Dziewczyna z pociągu", "Maria", "Konopnicka", 2016, Gatunek.THRILLER));
        zb.dodajKsiazkeDoBiblioteki(new Ksiazka("Nad Niemnem", "Eliza", "Orzeszkowa", 2016, Gatunek.HORROR));



    }
}
