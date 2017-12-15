package com.sda.model;


import javax.xml.bind.annotation.*;
import java.util.UUID;

/**
 * KONSTRUKTOR DO WCZYTYWANIA DANYCH Z PLIKU
 * tytul
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
    private String tytul;

    @XmlElement(name = "rok_wydania", required = true)
    private Integer rokWydania;

    @XmlElement(name = "dostepna", required = true)
    private Boolean dostepna;

    @XmlElement(name = "ID", required = true)
    private String ID;

    @XmlElements(@XmlElement(name = "autor", required = true))
    private Autor autor;

    @XmlElement(name = "gatunek", required = true)
    private Gatunek gatunek;

    public Ksiazka(){}

    public Ksiazka(String tytul, int rokWydania, boolean dostepna, String ID, Autor autor, Gatunek gatunek) {
        this.tytul = tytul;
        this.rokWydania = rokWydania;
        this.dostepna = dostepna;
        this.ID = ID;
        this.autor = autor;
        this.gatunek = gatunek;
    }

    public Ksiazka(String tytul, String autorImie, String autorNazwisko, int rokWydania, Gatunek gatunek) {
        this.ID = UUID.randomUUID().toString();
        this.tytul = tytul;
        this.autor = new Autor(autorImie, autorNazwisko);
        this.rokWydania = rokWydania;
        this.gatunek = gatunek;
        this.dostepna = true;
    }



    public Ksiazka(String tytul, String autorImie, String autorNazwisko, int rokWydania, boolean dostepna, Gatunek gatunek, String ID) {
        this.tytul = tytul;

        this.autor = new Autor(autorImie, autorNazwisko );
        this.rokWydania = rokWydania;
        this.dostepna = dostepna;
        this.ID = ID;
        this.gatunek = gatunek;
    }

    public String getTytul() {
        return tytul;
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

    public Integer getRokWydania() {
        return rokWydania;
    }


    public Boolean getDostepna() {
        return dostepna;
    }

    public void setDostepna(Boolean dostepna) {
        this.dostepna = dostepna;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ksiazka ksiazka = (Ksiazka) o;

        if (rokWydania != ksiazka.rokWydania) return false;
        if (!tytul.equals(ksiazka.tytul)) return false;
        return autor.getNazwisko().equals(ksiazka.autor.getNazwisko());
    }

    @Override
    public int hashCode() {
        int result = tytul.hashCode();
        result = 31 * result + autor.getNazwisko().hashCode();
        result = 31 * result + autor.getImie().hashCode();
        result = 31 * result + rokWydania;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(""+autor);
        sb.append(", \'").append(tytul).append('\'');
        sb.append(", ").append(gatunek);
        sb.append("; ").append(rokWydania);
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
