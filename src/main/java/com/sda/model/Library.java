package com.sda.model;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> listaKsiazek;

    private List<User> listaUzytkownikow;

    private List<LogEntry> rejestrWypozyczen;

    public Library() {
        this.listaKsiazek = new ArrayList<>();
        this.listaUzytkownikow = new ArrayList<>();
        this.rejestrWypozyczen = new ArrayList<>();
    }

    public void addBook(Book book) {
        listaKsiazek.add(book);
    }

    public void addUser(User user) {
        listaUzytkownikow.add(user);
    }

    public List<Book> getBooksList() {
        return listaKsiazek;
    }
    public List<User> getListaUzytkownikow() {
        return listaUzytkownikow;
    }

    public List<LogEntry> getRegistry() {
        return rejestrWypozyczen;
    }


    public void addEntry(LogEntry wp) {
        rejestrWypozyczen.add(wp);
    }
}
