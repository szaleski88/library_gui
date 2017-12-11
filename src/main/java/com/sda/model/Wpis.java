package com.sda.model;

import com.sda.model.Ksiazka;
import com.sda.model.Uzytkownik;

import java.time.LocalDate;

public class Wpis {

    private Ksiazka ksiazka;
    private Uzytkownik uzytkownik;
    private LocalDate dataWypozyczenia;
    private LocalDate dataZwrotu;

    public Wpis(Ksiazka ksiazka, Uzytkownik uzytkownik, LocalDate dataWypozyczenia, LocalDate dataZwrotu) {
        this.ksiazka = ksiazka;
        this.uzytkownik = uzytkownik;
        this.dataWypozyczenia = dataWypozyczenia;
        this.dataZwrotu = dataZwrotu;
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
}
