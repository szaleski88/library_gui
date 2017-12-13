package com.sda.controller;

import com.sda.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ZarzadzanieBiblioteka {

    private static Biblioteka biblioteka;

    public ZarzadzanieBiblioteka(Biblioteka biblio){
        biblioteka = biblio;
    }
    public static void dodajKsiazkeDoBiblioteki(Ksiazka ksiazka){
        biblioteka.dodajKsiazke(ksiazka);
    }

    public static List<String> zwrocTytulyKsiazek() {
        return biblioteka.getListaKsiazek().stream().map(ksiazka -> ksiazka.getTytul()).collect(Collectors.toList());
    }

    public static void wypozyczKsiazke(Ksiazka ksiazka, Uzytkownik uzytkownik){
        ksiazka.setDostepna(false);
        Wpis wpis = new Wpis(ksiazka, uzytkownik, LocalDate.now(), null);
        biblioteka.getRejestrWypozyczen().add(wpis);
    }

    public void zwrocKsiazke(Ksiazka ksiazka){

    }

    public void wyswietlListeKsiazek() {

        for (Ksiazka ksiazka : biblioteka.getListaKsiazek()){
            System.out.println(ksiazka);
        }
    }

    public void wyszukajPoTytule(String tytul){

    }

    //wyszukajPoAutorze(Autor autor);

    //wyszukajPoAutorze(String imie, String nazwisko);

    public static void  dodajUzytkownika(Uzytkownik uzytkownik){
        biblioteka.dodajUzytkownika(uzytkownik);
    }

    public static void dodajUzytkownika(String imie, String nazwisko, Plec plec){
        biblioteka.dodajUzytkownika(new Uzytkownik(imie, nazwisko, plec));
    }

    public static void  wyswietlWypozyczoneKsiazki(){

        List<Ksiazka> wypozyczoneKsiazki = biblioteka.getRejestrWypozyczen().stream()
                .filter(wpis -> !wpis.getKsiazka().getDostepna()).map(Wpis::getKsiazka)
                .collect(Collectors.toList());

        System.out.println("AKTUALNIE WYPOZYCZONE KSIAZKI:");
        for (int i = 0; i < wypozyczoneKsiazki.size(); i++) {
            System.out.println(i+". " + wypozyczoneKsiazki.get(i));
        }

    }

    public static void wyswietlWypozyczonePrzezUzytkownika(Uzytkownik uzytkownik){

        List<Wpis> rejestr = biblioteka.getRejestrWypozyczen();
        List<Ksiazka> wypozyczoneUzytkownika = rejestr.stream().filter(wpis -> wpis.getUzytkownik()
                .equals(uzytkownik) && !wpis.getKsiazka().getDostepna())
                .map(Wpis::getKsiazka).collect(Collectors.toList());

        System.out.println("AKTUALNIE WYPOZYCZONE KSIAZKI PRZEZ: " + uzytkownik.toString());
        for (int i = 0; i < wypozyczoneUzytkownika.size(); i++) {
            System.out.println(i+". " + wypozyczoneUzytkownika.get(i));
        }
    }


}
