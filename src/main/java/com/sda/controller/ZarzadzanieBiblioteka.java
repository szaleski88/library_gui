package com.sda.controller;

import com.sda.model.Biblioteka;
import com.sda.model.Ksiazka;
import com.sda.model.RejestrWypozyczen;
import com.sda.model.Uzytkownik;

import java.util.List;
import java.util.stream.Collectors;

public class ZarzadzanieBiblioteka {

    public static void dodajKsiazkeDoBiblioteki(Ksiazka ksiazka, Biblioteka biblioteka){
        biblioteka.dodajKsiazke(ksiazka);
    }

    public static List<String> zwrocTytulyKsiazek(Biblioteka biblioteka) {
        return biblioteka.getListaKsiazek().stream().map(ksiazka -> ksiazka.getTytul()).collect(Collectors.toList());
    }

    public static void wypozyczKsiazke(Ksiazka ksiazka, Uzytkownik uzytkownik, RejestrWypozyczen rejestr){
    }

    public void zwrocKsiazke(Ksiazka ksiazka, RejestrWypozyczen rejestr){

    }

}
