package com.sda.components;

import java.util.List;

public class Uzytkownik implements  Comparable<Uzytkownik>{

    private String imie;
    private String nazwisko;
    private Adres adres;
    private List<Ksiazka> wypozyczoneKsiazki;


    public Uzytkownik(String imie, String nazwisko, Adres adres) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
    }


    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public void setWypozyczoneKsiazki(List<Ksiazka> wypozyczoneKsiazki) {
        this.wypozyczoneKsiazki = wypozyczoneKsiazki;
    }

    public List<Ksiazka> getWypozyczoneKsiazki() {
        return wypozyczoneKsiazki;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Uzytkownik{");
        sb.append("imie='").append(imie).append('\'');
        sb.append(", nazwisko='").append(nazwisko).append('\'');
        sb.append(", adres=").append(adres);
        sb.append('}');
        return sb.toString();
    }


    public int compareTo(Uzytkownik o) {
       return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Uzytkownik that = (Uzytkownik) o;

        if (!imie.equals(that.imie)) return false;
        if (!nazwisko.equals(that.nazwisko)) return false;
        return adres.equals(that.adres);
    }

    @Override
    public int hashCode() {
        int result = imie.hashCode();
        result = 31 * result + nazwisko.hashCode();
        result = 31 * result + adres.hashCode();
        return result;
    }
}
