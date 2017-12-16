package com.sda.model;

import javax.xml.bind.annotation.*;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "uzytkownik")
public class Uzytkownik implements  Comparable<Uzytkownik>{

    @XmlElement(name = "imie", required = true)
    private String imie;
    @XmlElement(name = "nazwisko", required = true)
    private String nazwisko;
    @XmlElement(name = "id", required = true)
    private String id;
    @XmlElement(name = "plec", required = true)
    private Plec plec;
//    private Adres adres;
    public Uzytkownik(){

    }


    public Uzytkownik(String imie, String nazwisko, Plec plec) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
        id = UUID.randomUUID().toString();
    }

    public Uzytkownik(String imie, String nazwisko, String id, Plec plec) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.id = id;
        this.plec = plec;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Plec getPlec() {
        return plec;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(imie);
        sb.append(" ").append(nazwisko);
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
        return !nazwisko.equals(that.nazwisko);
    }

    @Override
    public int hashCode() {
        int result = imie.hashCode();
        result = 31 * result + nazwisko.hashCode();
//        result = 31 * result + adres.hashCode();
        return result;
    }
}
