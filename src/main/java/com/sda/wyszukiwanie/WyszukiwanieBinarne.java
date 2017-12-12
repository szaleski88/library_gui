package com.sda.wyszukiwanie;


import java.util.ArrayList;
import java.util.List;

public class WyszukiwanieBinarne implements Wyszukiwanie {

    /**
     * Wykorzystanie tej metody TYLKO dla posortowanych tablic
     * @param liczba szukana liczba
     * @param tablica tablica elementow
     * @return indeks wejsciowej tablicy pod ktorym jest szukana liczba. -1 jesli nie znaleziono.
     */
    private int[] tablicaDoPrzeszukania;
    private String nazwaWyszukiwarki;
    private int licznikWywolan = 0;
    private List<Integer> trafienia = new ArrayList<>();


    public WyszukiwanieBinarne() {
        this.nazwaWyszukiwarki = "Wyszukiwanie binarne";
    }

    public WyszukiwanieBinarne(int[] tablicaDoPrzeszukania, String nazwaWyszukiwarki) {
        this.tablicaDoPrzeszukania = tablicaDoPrzeszukania;
        this.nazwaWyszukiwarki = nazwaWyszukiwarki;
    }

    public String getNazwaWyszukiwarki() {
        return nazwaWyszukiwarki;
    }

    @Override
    public List<Integer> szukajWszystkie(int liczba) {

        int pierwszyTraf = szukaj(liczba, tablicaDoPrzeszukania);
        if (pierwszyTraf == -1) return trafienia;

        trafienia.add(pierwszyTraf);
        for (int i = pierwszyTraf + 1; i < tablicaDoPrzeszukania.length; i++) {
            if (tablicaDoPrzeszukania[i] == liczba ) trafienia.add(i);
            else break;
        }

        for (int i = pierwszyTraf -1 ; i > 0; i--) {
            if (tablicaDoPrzeszukania[i] == liczba ) trafienia.add(i);
            else break;
        }
        return trafienia;
        }

    @Override
    public int getLicznikWywolan() {
        return licznikWywolan;
    }

    public int szukaj(int liczba, int[] tablica) {

        licznikWywolan++;
        if (tablica==null || tablica.length == 0) return -1;
        int przesuniecie = 0;
        int szukanaLiczba;
        int index = tablica.length / 2;

        if ( tablica[index] == liczba ) {
            return index;
        } else if ( tablica[index] > liczba ) {
            int[] lewa = new int[index];

            for (int i = 0; i < index ; i++) {
                lewa[i] = tablica[i];
            }

            szukanaLiczba = szukaj(liczba, lewa);
        } else {
            int[] prawa = new int[tablica.length - index -1];

            // NOWOSC!
            for (int i = index + 1, j = 0; i < tablica.length ; i++, j++ ) {
                prawa[j] = tablica[i];
            }
            przesuniecie = index + 1;
            szukanaLiczba = szukaj(liczba, prawa);
            if ( szukanaLiczba != -1 ) {
                szukanaLiczba += przesuniecie;
            }
        }
        return szukanaLiczba;
    }

    public int szukaj(int liczba) {
        return szukaj(liczba, tablicaDoPrzeszukania);
    }

    public static void main(String[] args) {
        int[] tablica = {5,16,1231,56,754,32,6,90,11,2,10};


    }
}
