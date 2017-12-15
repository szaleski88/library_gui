package com.sda.model;

import com.sda.model.Ksiazka;
import com.sda.model.Uzytkownik;

import java.time.LocalDate;

public class Wpis {

    private Ksiazka ksiazka;
    private Uzytkownik uzytkownik;
    private LocalDate dataWypozyczenia;
    private LocalDate dataZwrotu;
    private Autor autor;
    private String tytul;

    public Wpis(){}

    public Wpis(Ksiazka ksiazka, Uzytkownik uzytkownik, LocalDate dataWypozyczenia, LocalDate dataZwrotu) {
        this.ksiazka = ksiazka;
        this.uzytkownik = uzytkownik;
        this.dataWypozyczenia = dataWypozyczenia;
        this.autor = ksiazka.getAutor();
        this.dataZwrotu = dataZwrotu;
        this.tytul = ksiazka.getTytul();
    }

    public String getIdKsiazki() {
        return ksiazka.getID();
    }

    public void setKsiazka(Ksiazka ksiazka) {
        this.ksiazka = ksiazka;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public LocalDate getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(LocalDate dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public LocalDate getDataZwrotu() {
        return dataZwrotu;
    }

    public void setDataZwrotu(LocalDate dataZwrotu) {
        this.dataZwrotu = dataZwrotu;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Ksiazka getKsiazka() {
        return ksiazka;
    }
}
