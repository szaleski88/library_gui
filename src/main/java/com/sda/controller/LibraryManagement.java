package com.sda.controller;

import com.sda.model.*;
import com.sda.search.BinarySearch;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryManagement {

    private static Library library;


    public LibraryManagement(Library library){

        library = library;
    }

    private void dodajKsiazkeDoBiblioteki(Book book){

        library.addBook(book);
    }

    public List<String> getBooksTitles() {
        return library.getBooksList().stream().map(book -> book.getTitle()).collect(Collectors.toList());
    }

    public void borrowBook(Book book, User user){
        book.setAvailable(false);
        LogEntry logEntry = new LogEntry(book, user, LocalDate.now(), null);
        library.getRegistry().add(logEntry);
    }

    public void returnBorrowedBook(Book book, User user){
        Optional<LogEntry> rejWyp = library.getRegistry().stream()
                .filter(logEntry -> logEntry.getBook().equals(book) && logEntry.getUser().equals(user) && !logEntry.getBook()
                        .getAvailable()).findFirst();
        if ( rejWyp.isPresent()) {
            LogEntry logEntry = rejWyp.get();
            logEntry.setReturnDate(LocalDate.now());
            logEntry.getBook().setAvailable(true);
            System.out.println("Book: {" + book.toString() + "} was successfully returned!");
        } else {
            System.out.println("An Error occured while returning a book!!!");
        }
    }

    public void displayBooksList() {

        for (Book book : library.getBooksList()){
            System.out.println(book);
        }
    }

    public List<Book> searchByTitle(String title){
        BinarySearch bs = new BinarySearch(library);
        return bs.searchByTitle(title);

    }

    public List<Book> searchByAuthor(String firstName, String lastName){
        BinarySearch bs = new BinarySearch(library);
        return bs.szukajAutora(firstName, lastName);
    }

    public void addUser(User user){
        library.addUser(user);
    }

    public void addUser(String firstName, String lastName, Gender gender){
        // sprawdzenie czy uzytkownik juz istnieje?
        library.addUser(new User(firstName, lastName, gender));
    }

    public void displayAllBorrowedBooks(){

        List<Book> borrowedBooks = library.getRegistry().stream()
                .filter(logEntry -> !logEntry.getBook().getAvailable()).map(LogEntry::getBook)
                .collect(Collectors.toList());

        System.out.println("Currently borrowed books:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.println(i+". " + borrowedBooks.get(i));
        }

    }

    public void displayBooksBorrowedByUser(User user){

        List<LogEntry> registry = library.getRegistry();
        List<Book> borrowedByUser = registry.stream().filter(logEntry -> logEntry.getUser()
                .equals(user) && !logEntry.getBook().getAvailable())
                .map(LogEntry::getBook).collect(Collectors.toList());

        System.out.println("Books borrowed by : " + user.toString());
        for (int i = 0; i < borrowedByUser.size(); i++) {
            System.out.println(i+". " + borrowedByUser.get(i));
        }
    }
    public List<LogEntry> getBorrowedBooks() {

        return library.getRegistry().stream()
                .filter(logEntry -> !logEntry.getBook().getAvailable())
                .collect(Collectors.toList());
    }

    public List<LogEntry> getBorrowedByUser(String firstName, String lastName){

        List<LogEntry> registry = library.getRegistry();

        List<LogEntry> borrowedBooks =  registry.stream().filter(logEntry -> logEntry.getUser().getImie().equalsIgnoreCase(firstName) &&
                logEntry.getUser().getLastName().equalsIgnoreCase(lastName) && !logEntry.getBook().getAvailable())
                .collect(Collectors.toList());

        if (borrowedBooks.size() == 0 ) {
            return null;
        } else {
            return borrowedBooks;
        }
    }


    public static void main(String[] args) {

        Library library = new Library();
        Backup b = new Backup();
        LibraryManagement zb = new LibraryManagement(library);

        try {
            b.readBooksFromFile(library);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        zb.dodajKsiazkeDoBiblioteki(new Book("Dziewczyna z pociągu", "Maria", "Konopnicka", 2016, Genre.THRILLER));
        zb.dodajKsiazkeDoBiblioteki(new Book("Nad Niemnem", "Eliza", "Orzeszkowa", 2016, Genre.HORROR));



    }
}
