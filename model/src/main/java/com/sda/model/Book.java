package com.sda.model;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * KONSTRUKTOR DO WCZYTYWANIA DANYCH Z PLIKU
 * title
 * autorImie
 * autorNazwisko
 * releaseDate
 * genre
 * ID
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "book")
public class Book implements Comparable<Book> {

    @XmlElement(name = "title", required = true)
    private String title;

    @XmlElement(name = "release_date", required = true)
    private Integer releaseDate;

    @XmlElement(name = "available", required = true)
    private Boolean available;

    @XmlElement(name = "ID", required = true)
    private String ID;

    @XmlElements(@XmlElement(name = "author", required = true))
    private Author author;

    @XmlElement(name = "genre", required = true)
    private Genre genre;

    public Book() {
    }

    public Book(String title, int releaseDate, boolean available, String ID, Author author, Genre genre) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.available = available;
        this.ID = ID;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, String authorFirstName, String authorLastName, int releaseDate, Genre genre) {
        this.ID = UUID.randomUUID().toString();
        this.title = title;
        this.author = new Author(authorFirstName, authorLastName);
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.available = true;
    }

    public Book(String title, String authorFirstName, String authorLastName, int releaseDate, boolean available, Genre genre, String ID) {
        this.title = title;

        this.author = new Author(authorFirstName, authorLastName);
        this.releaseDate = releaseDate;
        this.available = available;
        this.ID = ID;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
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

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean avaiable) {
        this.available = avaiable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (releaseDate != book.releaseDate) return false;
        if (!title.equals(book.title)) return false;
        return author.getLastName().equals(book.author.getLastName());
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.getLastName().hashCode();
        result = 31 * result + author.getFirstName().hashCode();
        result = 31 * result + releaseDate;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("" + author);
        sb.append(", \'").append(title).append('\'');
        sb.append(", ").append(genre);
        sb.append("; ").append(releaseDate);
        return sb.toString();
    }

    @Override
    public int compareTo(Book o) {

        if (this.equals(o)) return 0;
        if (this.author.compareTo(o.getAuthor()) == 0 && this.getTitle().equalsIgnoreCase(o.getTitle())) {
            return 0;
        } else if (this.author.compareTo(o.getAuthor()) == 0) {
            return this.getTitle().compareToIgnoreCase(o.getTitle());
        } else return this.getAuthor().compareTo(o.getAuthor());

    }
}
