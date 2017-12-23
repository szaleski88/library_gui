package com.sda.model;


import javax.xml.bind.annotation.*;
import java.util.UUID;

/**
 * KONSTRUKTOR DO WCZYTYWANIA DANYCH Z PLIKU
 * tytul
 * autorImie
 * autorNazwisko
 * rokWydania
 * genre
 * ID
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ksiazka")
public class Book implements Comparable<Book>{

    @XmlElement(name = "tytul", required = true)
    private String tytul;

    @XmlElement(name = "rok_wydania", required = true)
    private Integer rokWydania;

    @XmlElement(name = "dostepna", required = true)
    private Boolean dostepna;

    @XmlElement(name = "ID", required = true)
    private String ID;

    @XmlElements(@XmlElement(name = "author", required = true))
    private Author author;

    @XmlElement(name = "genre", required = true)
    private Genre genre;

    public Book(){}

    public Book(String tytul, int rokWydania, boolean dostepna, String ID, Author author, Genre genre) {
        this.tytul = tytul;
        this.rokWydania = rokWydania;
        this.dostepna = dostepna;
        this.ID = ID;
        this.author = author;
        this.genre = genre;
    }

    public Book(String tytul, String autorImie, String autorNazwisko, int rokWydania, Genre genre) {
        this.ID = UUID.randomUUID().toString();
        this.tytul = tytul;
        this.author = new Author(autorImie, autorNazwisko);
        this.rokWydania = rokWydania;
        this.genre = genre;
        this.dostepna = true;
    }



    public Book(String tytul, String autorImie, String autorNazwisko, int rokWydania, boolean dostepna, Genre genre, String ID) {
        this.tytul = tytul;

        this.author = new Author(autorImie, autorNazwisko );
        this.rokWydania = rokWydania;
        this.dostepna = dostepna;
        this.ID = ID;
        this.genre = genre;
    }

    public String getTytul() {
        return tytul;
    }

    public String getID() {
        return ID;
    }

    public Genre getGenre() {
        return genre;
    }

    public Author getAuthor() {
        return author;
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

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (rokWydania != book.rokWydania) return false;
        if (!tytul.equals(book.tytul)) return false;
        return author.getNazwisko().equals(book.author.getNazwisko());
    }

    @Override
    public int hashCode() {
        int result = tytul.hashCode();
        result = 31 * result + author.getNazwisko().hashCode();
        result = 31 * result + author.getImie().hashCode();
        result = 31 * result + rokWydania;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(""+ author);
        sb.append(", \'").append(tytul).append('\'');
        sb.append(", ").append(genre);
        sb.append("; ").append(rokWydania);
        return sb.toString();
    }

    @Override
    public int compareTo(Book o) {

        if (this.equals(o) ) return 0;
        if (this.author.compareTo(o.getAuthor()) == 0 && this.getTytul().equalsIgnoreCase(o.getTytul())) {
            return 0;
        }

        else if ( this.author.compareTo(o.getAuthor()) == 0 ) {
            return this.getTytul().compareToIgnoreCase(o.getTytul());
        }
        else return this.getAuthor().compareTo(o.getAuthor());

    }
}
