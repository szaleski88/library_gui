package com.sda.wyszukiwanie;


import com.sda.model.Autor;
import com.sda.model.Biblioteka;
import com.sda.model.Ksiazka;
import com.sda.sortowanie.Sortowanie;
import com.sda.sortowanie.SortowanieSzybkie;

import java.util.ArrayList;
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


    public WyszukiwanieBinarne(Biblioteka biblioreka) {
//        this.wszystkieKsiazki = sortowanie.sortuj(ksiazki);
        this.biblioteka = biblioteka;
    }

    @Override
    public List<Ksiazka> szukajTytul(String tytul) {
        // ZACIĄGNIĘCIE I POSORTOWANIE AKTUALNEJ LISTY KSIĄZEK
        this.wszystkieKsiazki = sortowanie.sortuj(biblioteka.getListaKsiazek());
        int pierwszyTraf = szukajTytulu(tytul, wszystkieKsiazki);
        if (pierwszyTraf == -1) return trafienia;

        trafienia.add(wszystkieKsiazki.get(pierwszyTraf));
        for (int i = pierwszyTraf + 1; i < wszystkieKsiazki.size(); i++) {
            if (wszystkieKsiazki.get(i).getTytul().equalsIgnoreCase(tytul)) trafienia.add(wszystkieKsiazki.get(i));
            else break;
        }

        for (int i = pierwszyTraf -1 ; i > 0; i--) {
            if (wszystkieKsiazki.get(i).getTytul().equalsIgnoreCase(tytul)) trafienia.add(wszystkieKsiazki.get(i));
            else break;
        }
        return trafienia;
    }


    @Override
    public List<Ksiazka> szukajAutora(Autor autor) {
        return null;
    }

    @Override
    public List<Ksiazka> szukajAutora(String imie, String nazwisko) {
        return null;
    }




    public int szukajTytulu(String tytul, List<Ksiazka> ksiazki) {

        if (ksiazki==null || ksiazki.size() == 0) return -1;
        int przesuniecie = 0;
        int szukanaLiczba;
        int index = ksiazki.size() / 2;

        if ( ksiazki.get(index).getTytul().equalsIgnoreCase(tytul) ) {
            return index;
        } else if ( ksiazki.get(index).getTytul().compareToIgnoreCase(tytul) > 0 ) {
            List<Ksiazka> lewa = new ArrayList<>();

            for (int i = 0; i < index ; i++) {
                lewa.set(i, ksiazki.get(i));
            }

            szukanaLiczba = szukajTytulu(tytul, lewa);
        } else {
            List<Ksiazka> prawa = new ArrayList<>();

            // NOWOSC!
            for (int i = index + 1, j = 0; i < ksiazki.size() ; i++, j++ ) {
                prawa.set(j, ksiazki.get(i));
            }
            przesuniecie = index + 1;
            szukanaLiczba = szukajTytulu(tytul, prawa);
            if ( szukanaLiczba != -1 ) {
                szukanaLiczba += przesuniecie;
            }
        }
        return szukanaLiczba;
    }



//    public int szukaj(int liczba) {
//        return szukaj(liczba, tablicaDoPrzeszukania);
//    }

    public static void main(String[] args) {
        int[] tablica = {5,16,1231,56,754,32,6,90,11,2,10};
    }
}
