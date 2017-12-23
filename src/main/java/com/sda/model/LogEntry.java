package com.sda.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="wpis")
public class LogEntry {

    @XmlElement(name = "book", required = true)
    private Book book;
    @XmlElement(name = "user", required = true)
    private User user;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name = "dataWypozyczenia", required = true)
    private LocalDate dataWypozyczenia;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name = "dataZwrotu", required = true)
    private LocalDate dataZwrotu;
    @XmlElement(name = "author", required = true)
    private Author author;
    @XmlElement(name = "tytul", required = true)
    private String tytul;

    public LogEntry(){}

    public LogEntry(Book book, User user, LocalDate dataWypozyczenia, LocalDate dataZwrotu) {
        this.book = book;
        this.user = user;
        this.dataWypozyczenia = dataWypozyczenia;
        this.author = book.getAuthor();
        this.dataZwrotu = dataZwrotu;
        this.tytul = book.getTytul();
    }

    public String getIdKsiazki() {
        return book.getID();
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }
}
