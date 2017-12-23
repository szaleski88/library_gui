package com.sda.model;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> listaKsiazek;

    private List<User> listaUzytkownikow;

    private List<RegEntry> rejestrWypozyczen;

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
    public List<User> getUsersList() {
        return listaUzytkownikow;
    }

    public List<RegEntry> getRegistry() {
        return rejestrWypozyczen;
    }


    public void addRegEntry(RegEntry wp) {
        rejestrWypozyczen.add(wp);
    }
}
