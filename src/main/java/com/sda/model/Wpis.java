package com.sda.model;

import com.sda.model.Ksiazka;
import com.sda.model.Uzytkownik;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="wpis")
public class Wpis {

    @XmlElement(name = "ksiazka", required = true)
    private Ksiazka ksiazka;
    @XmlElement(name = "uzytkownik", required = true)
    private Uzytkownik uzytkownik;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name = "dataWypozyczenia", required = true)
    private LocalDate dataWypozyczenia;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name = "dataZwrotu", required = true)
    private LocalDate dataZwrotu;
    @XmlElement(name = "autor", required = true)
    private Autor autor;
    @XmlElement(name = "tytul", required = true)
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

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }
}
