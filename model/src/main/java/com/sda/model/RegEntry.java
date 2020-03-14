package com.sda.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "regEntry")
public class RegEntry {

    @XmlElement(name = "book", required = true)
    private Book book;
    @XmlElement(name = "user", required = true)
    private User user;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name = "dateBorrowed", required = true)
    private LocalDate dateBorrowed;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name = "dateReturned", required = true)
    private LocalDate dateReturned;
    @XmlElement(name = "author", required = true)
    private Author author;
    @XmlElement(name = "title", required = true)
    private String title;

    public RegEntry() {
    }

    public RegEntry(Book book, User user, LocalDate dateBorrowed, LocalDate dateReturned) {
        this.book = book;
        this.user = user;
        this.dateBorrowed = dateBorrowed;
        this.author = book.getAuthor();
        this.dateReturned = dateReturned;
        this.title = book.getTitle();
    }

    public String getBookId() {
        return book.getID();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(LocalDate dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setReturnDate(LocalDate dataZwrotu) {
        this.dateReturned = dataZwrotu;
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

    public void setBook(Book book) {
        this.book = book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
