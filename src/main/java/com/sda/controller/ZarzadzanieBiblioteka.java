package com.sda.controller;

import com.sda.model.Biblioteka;
import com.sda.model.Ksiazka;

import java.util.List;
import java.util.stream.Collectors;

public class ZarzadzanieBiblioteka {

    public static void dodajKsiazkeDoBiblioteki(Ksiazka ksiazka, Biblioteka biblioteka){
        biblioteka.dodajKsiazke(ksiazka);
    }

    public static List<String> zwrocTytulyKsiazek(Biblioteka biblioteka) {
        return biblioteka.getListaKsiazek().stream().map(ksiazka -> ksiazka.getTytul()).collect(Collectors.toList());
    }

}
