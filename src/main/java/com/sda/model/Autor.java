package com.sda.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="autor")
public class Autor implements Comparable<Autor>{

    @XmlElement(name = "imie")
    private String imie;
    @XmlElement(name = "nazwisko")
    private String nazwisko;

    public Autor(){}

    public Autor(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;

    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(imie);
        sb.append(" ").append(nazwisko);
        return sb.toString();
    }

    @Override
    public int compareTo(Autor o) {
        if (this.equals(o)) return 0;
        if (this.getNazwisko().equalsIgnoreCase(o.getNazwisko())) {
            return this.getImie().compareToIgnoreCase(o.getImie());
        }
        return this.getNazwisko().compareToIgnoreCase(o.getNazwisko());

    }
}
