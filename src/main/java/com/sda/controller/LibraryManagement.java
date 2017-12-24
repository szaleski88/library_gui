package com.sda.controller;

import com.sda.model.*;
import com.sda.search.BinarySearch;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryManagement {

    private static Library library;


    public static Library getLibrary() {
        return library;
    }


    public static void initLibrary(){
        library = new Library();
        try{
            Backup.readBooksFromFile(library);
            Backup.readUsersFromFile(library);
            Backup.readRegistryFromFile(library);
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
    }

    private void addBookToLibrary(Book book){

        library.addBook(book);
    }

    public List<String> getBooksTitles() {
        return library.getAllBooks().stream().map(book -> book.getTitle()).collect(Collectors.toList());
    }

    public static void borrowBook(Book book, User user){
        book.setAvailable(false);
        RegEntry regEntry = new RegEntry(book, user, LocalDate.now(), null);
        library.getRegistry().add(regEntry);
    }

    public void returnBorrowedBook(Book book, User user){
        Optional<RegEntry> rejWyp = library.getRegistry().stream()
                .filter(logEntry -> logEntry.getBook().equals(book) && logEntry.getUser().equals(user) && !logEntry.getBook()
                        .getAvailable()).findFirst();
        if ( rejWyp.isPresent()) {
            RegEntry regEntry = rejWyp.get();
            regEntry.setReturnDate(LocalDate.now());
            regEntry.getBook().setAvailable(true);
            System.out.println("Book: {" + book.toString() + "} was successfully returned!");
        } else {
            System.out.println("An Error occured while returning a book!!!");
        }
    }

    public void displayBooksList() {

        for (Book book : library.getAllBooks()){
            System.out.println(book);
        }
    }

    public static List<Book> searchByTitle(String title){
        BinarySearch bs = new BinarySearch(library);
        return bs.searchByTitle(title);

    }

    public static List<Book> searchByAuthor(String firstName, String lastName){
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
                .filter(logEntry -> !logEntry.getBook().getAvailable()).map(RegEntry::getBook)
                .collect(Collectors.toList());

        System.out.println("Currently borrowed books:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.println(i+". " + borrowedBooks.get(i));
        }

    }

    public void displayBooksBorrowedByUser(User user){

        List<RegEntry> registry = library.getRegistry();
        List<Book> borrowedByUser = registry.stream().filter(logEntry -> logEntry.getUser()
                .equals(user) && !logEntry.getBook().getAvailable())
                .map(RegEntry::getBook).collect(Collectors.toList());

        System.out.println("Books borrowed by : " + user.toString());
        for (int i = 0; i < borrowedByUser.size(); i++) {
            System.out.println(i+". " + borrowedByUser.get(i));
        }
    }
    public static List<RegEntry> getBorrowedBooks() {

        return library.getRegistry().stream()
                .filter(logEntry -> !logEntry.getBook().getAvailable())
                .collect(Collectors.toList());
    }

    public static List<RegEntry> getBorrowedByUser(String firstName, String lastName){

        List<RegEntry> registry = library.getRegistry();

        List<RegEntry> borrowedBooks =  registry.stream().filter(logEntry -> logEntry.getUser().getFirstName().equalsIgnoreCase(firstName) &&
                logEntry.getUser().getLastName().equalsIgnoreCase(lastName) && !logEntry.getBook().getAvailable())
                .collect(Collectors.toList());

        if (borrowedBooks.size() == 0 ) {
            return null;
        } else {
            return borrowedBooks;
        }
    }

}
