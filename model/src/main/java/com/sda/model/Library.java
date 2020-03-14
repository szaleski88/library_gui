package com.sda.model;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> allBooks;

    private List<User> allUsers;

    private List<RegEntry> registry;

    public Library() {
        this.allBooks = new ArrayList<>();
        this.allUsers = new ArrayList<>();
        this.registry = new ArrayList<>();
    }

    public void addBook(Book book) {
        allBooks.add(book);
    }

    public void addUser(User user) {
        allUsers.add(user);
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public List<User> getUsersList() {
        return allUsers;
    }

    public List<RegEntry> getRegistry() {
        return registry;
    }

    public void addRegEntry(RegEntry wp) {
        registry.add(wp);
    }
}
