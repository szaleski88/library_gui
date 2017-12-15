package com.sda.wyszukiwanie;


import com.sda.controller.Backup;
import com.sda.controller.ZarzadzanieBiblioteka;
import com.sda.model.Autor;
import com.sda.model.Biblioteka;
import com.sda.model.Ksiazka;
import com.sda.sortowanie.Sortowanie;
import com.sda.sortowanie.SortowanieSzybkie;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WyszukiwanieBinarne implements Wyszukiwanie {

    /**
     * Wykorzystanie tej metody TYLKO dla posortowanych tablic
     * @param liczba szukana liczba
     * @param tablica tablica elementow
     * @return indeks wejsciowej tablicy pod ktorym jest szukana liczba. -1 jesli nie znaleziono.
     */
    private List<Ksiazka> wszystkieKsiazki;
    private Biblioteka biblioteka;
    private Sortowanie sortowanie = new SortowanieSzybkie();
    private List<Ksiazka> trafienia = new ArrayList<>();


    public WyszukiwanieBinarne(Biblioteka biblioteka) {
//        this.wszystkieKsiazki = sortowanie.sortuj(ksiazki);
        this.biblioteka = biblioteka;
    }

    @Override
    public List<Ksiazka> szukajTytul(String tytul) {
        // ZACIĄGNIĘCIE I POSORTOWANIE AKTUALNEJ LISTY KSIĄZEK
        //this.wszystkieKsiazki = sortowanie.sortuj(biblioteka.getListaKsiazek());
        this.wszystkieKsiazki = biblioteka.getListaKsiazek();

        this.wszystkieKsiazki.sort((a, b) -> {
            String tytul1 = a.getTytul();
            String tytul2 = b.getTytul();
            return tytul1.compareToIgnoreCase(tytul2);
        });

        int pierwszyTraf = szukajTytuluRekurencyjnie(tytul, wszystkieKsiazki);
        if (pierwszyTraf == -1) return trafienia;

        trafienia.add(wszystkieKsiazki.get(pierwszyTraf));
        for (int i = pierwszyTraf + 1; i < wszystkieKsiazki.size(); i++) {
            if (wszystkieKsiazki.get(i).getTytul().equalsIgnoreCase(tytul)) trafienia.add(wszystkieKsiazki.get(i));
            else break;
        }

        for (int i = pierwszyTraf - 1 ; i >= 0; i--) {
            if (wszystkieKsiazki.get(i).getTytul().equalsIgnoreCase(tytul)) trafienia.add(wszystkieKsiazki.get(i));
            else break;
        }
        return trafienia;
    }

    @Override
    public List<Ksiazka> szukajAutora(String imie, String nazwisko) {
        // ZACIĄGNIĘCIE I POSORTOWANIE AKTUALNEJ LISTY KSIĄZEK
        Autor autor = new Autor(imie, nazwisko);
        this.wszystkieKsiazki = sortowanie.sortuj(biblioteka.getListaKsiazek());
        int pierwszyTraf = szukajAutoraRekurencyjnie(autor, wszystkieKsiazki);
        if (pierwszyTraf == -1) return trafienia;

        trafienia.add(wszystkieKsiazki.get(pierwszyTraf));
        for (int i = pierwszyTraf + 1; i < wszystkieKsiazki.size(); i++) {
            if (wszystkieKsiazki.get(i).getAutor().equals(autor)) trafienia.add(wszystkieKsiazki.get(i));
            else break;
        }

        for (int i = pierwszyTraf - 1 ; i >= 0; i--) {
            if (wszystkieKsiazki.get(i).getAutor().equals(autor)) trafienia.add(wszystkieKsiazki.get(i));
            else break;
        }
        return trafienia;
    }


    private int szukajAutoraRekurencyjnie(Autor autor, List<Ksiazka> ksiazki) {
        if (ksiazki==null || ksiazki.size() == 0) return -1;
        int przesuniecie = 0;
        int szukanaLiczba;
        int index = ksiazki.size() / 2;

        if ( ksiazki.get(index).getAutor().equals(autor) ) {
            return index;
        } else if ( ksiazki.get(index).getAutor().compareTo(autor) < 0 ) {
            List<Ksiazka> lewa = new ArrayList<>();

            for (int i = 0; i < index ; i++) {
                lewa.add(i, ksiazki.get(i));
            }

            szukanaLiczba = szukajAutoraRekurencyjnie(autor, lewa);
        } else {
            List<Ksiazka> prawa = new ArrayList<>();

            // NOWOSC!
            for (int i = index + 1, j = 0; i < ksiazki.size() ; i++, j++ ) {
                prawa.add(j, ksiazki.get(i));
            }
            przesuniecie = index + 1;
            szukanaLiczba = szukajAutoraRekurencyjnie(autor, prawa);
            if ( szukanaLiczba != -1 ) {
                szukanaLiczba += przesuniecie;
            }
        }
        return szukanaLiczba;
    }

    private int szukajTytuluRekurencyjnie(String tytul, List<Ksiazka> ksiazki) {

        if (ksiazki==null || ksiazki.size() == 0) return -1;
        int przesuniecie = 0;
        int szukanaLiczba;
        int index = ksiazki.size() / 2;

        if ( ksiazki.get(index).getTytul().equalsIgnoreCase(tytul) ) {
            return index;
        } else if ( ksiazki.get(index).getTytul().compareToIgnoreCase(tytul) > 0 ) {
            List<Ksiazka> lewa = new ArrayList<>();

            for (int i = 0; i < index ; i++) {
                lewa.add(i, ksiazki.get(i));
            }

            szukanaLiczba = szukajTytuluRekurencyjnie(tytul, lewa);
        } else {
            List<Ksiazka> prawa = new ArrayList<>();

            // NOWOSC!
            for (int i = index + 1, j = 0; i < ksiazki.size() ; i++, j++ ) {
                prawa.add(j, ksiazki.get(i));
            }
            przesuniecie = index + 1;
            szukanaLiczba = szukajTytuluRekurencyjnie(tytul, prawa);
            if ( szukanaLiczba != -1 ) {
                szukanaLiczba += przesuniecie;
            }
        }
        return szukanaLiczba;
    }


    // MODUL TESTUJACY
    public static void main(String[] args) {

        Biblioteka biblioteka = new Biblioteka();
        Backup b = new Backup();

        try {
            b.odczytKsiazek(biblioteka);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(biblioteka.getListaKsiazek().size());
        WyszukiwanieBinarne wb = new WyszukiwanieBinarne(biblioteka);

        List<Ksiazka> ksiazki = wb.szukajTytul("Domowy Pan");
        //List<Ksiazka> autorzy = wb.szukajAutora("Adam", "Mickiewicz");

        for (Ksiazka ksiazka : ksiazki){
            System.out.println(ksiazka);
        }

    }
}
