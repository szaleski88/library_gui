package com.sda.model;

import com.sda.model.Ksiazka;
import com.sda.model.Uzytkownik;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

public class Biblioteka {

    private List<Ksiazka> listaKsiazek;

    private List<Uzytkownik> listaUzytkownikow;

    private List<Wpis> rejestrWypozyczen;

    public Biblioteka() {
        this.listaKsiazek = new ArrayList<>();
        this.listaUzytkownikow = new ArrayList<>();
        this.rejestrWypozyczen = new ArrayList<>();
    }

    public void dodajKsiazke(Ksiazka ksiazka) {
        listaKsiazek.add(ksiazka);
    }

    public void dodajUzytkownika (Uzytkownik uzytkownik) {
        listaUzytkownikow.add(uzytkownik);
    }

    public List<Ksiazka> getListaKsiazek() {
        return listaKsiazek;
    }
    public List<Uzytkownik> getListaUzytkownikow() {
        return listaUzytkownikow;
    }

    public List<Wpis> getRejestrWypozyczen() {
        return rejestrWypozyczen;
    }
}
