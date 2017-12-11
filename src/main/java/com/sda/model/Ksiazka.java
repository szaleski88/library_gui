package com.sda.model;


import javax.xml.bind.annotation.*;
import java.util.UUID;

/**
 * KONSTRUKTOR DO WCZYTYWANIA DANYCH Z PLIKU
 * Tytul
 * autorImie
 * autorNazwisko
 * rokWydania
 * gatunek
 * ID
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ksiazka")
public class Ksiazka implements Comparable<Ksiazka>{

    @XmlElement(name = "tytul", required = true)
    private String Tytul;

    @XmlElement(name = "rok_wydania", required = true)
    private int RokWydania;

    @XmlElement(name = "dostepna", required = true)
    private boolean Dostepna;

    @XmlElement(name = "ID", required = true)
    private String ID;

    @XmlElements(@XmlElement(name = "autor", required = true))
    private Autor autor;

    @XmlElement(name = "gatunek", required = true)
    private Gatunek gatunek;

    public Ksiazka(){}

    public Ksiazka(String tytul, int rokWydania, boolean dostepna, String ID, Autor autor, Gatunek gatunek) {
        this.Tytul = tytul;
        this.RokWydania = rokWydania;
        this.Dostepna = dostepna;
        this.ID = ID;
        this.autor = autor;
        this.gatunek = gatunek;
    }

    public Ksiazka(String tytul, String autorImie, String autorNazwisko, int rokWydania, Gatunek gatunek) {
        this.ID = UUID.randomUUID().toString();
        this.Tytul = tytul;
        this.autor = new Autor(autorImie, autorNazwisko);
        this.RokWydania = rokWydania;
        this.gatunek = gatunek;
    }



    public Ksiazka(String tytul, String autorImie, String autorNazwisko, int rokWydania, boolean dostepna, Gatunek gatunek, String ID) {
        this.Tytul = tytul;

        this.autor = new Autor(autorImie, autorNazwisko );
        this.RokWydania = rokWydania;
        this.Dostepna = dostepna;
        this.ID = ID;
        this.gatunek = gatunek;
    }

    public String getTytul() {
        return Tytul;
    }

    public String getID() {
        return ID;
    }

    public Gatunek getGatunek() {
        return gatunek;
    }

    public Autor getAutor() {
        return autor;
    }

    public int getRokWydania() {
        return RokWydania;
    }


    public boolean getDostepna() {
        return Dostepna;
    }

    public void setDostepna(boolean dostepna) {
        Dostepna = dostepna;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ksiazka ksiazka = (Ksiazka) o;

        if (RokWydania != ksiazka.RokWydania) return false;
        if (!Tytul.equals(ksiazka.Tytul)) return false;
        return autor.getNazwisko().equals(ksiazka.autor.getNazwisko());
    }

    @Override
    public int hashCode() {
        int result = Tytul.hashCode();
        result = 31 * result + autor.getNazwisko().hashCode();
        result = 31 * result + autor.getImie().hashCode();
        result = 31 * result + RokWydania;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(""+autor);
        sb.append(", \'").append(Tytul).append('\'');
        sb.append(", ").append(gatunek);
        sb.append("; ").append(RokWydania);
        return sb.toString();
    }

    @Override
    public int compareTo(Ksiazka o) {

        if (this.equals(o) ) return 0;
        if (this.autor.compareTo(o.getAutor()) == 0 && this.getTytul().equalsIgnoreCase(o.getTytul())) {
            return 0;
        }

        else if ( this.autor.compareTo(o.getAutor()) == 0 ) {
            return this.getTytul().compareToIgnoreCase(o.getTytul());
        }
        else return this.getAutor().compareTo(o.getAutor());

    }
}
