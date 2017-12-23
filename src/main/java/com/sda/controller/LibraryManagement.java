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


    public LibraryManagement(Library biblio){

        library = biblio;
    }

    private void dodajKsiazkeDoBiblioteki(Book book){

        library.dodajKsiazke(book);
    }

    public List<String> zwrocTytulyKsiazek() {
        return library.getListaKsiazek().stream().map(ksiazka -> ksiazka.getTytul()).collect(Collectors.toList());
    }

    public void wypozyczKsiazke(Book book, User user){
        book.setDostepna(false);
        LogEntry logEntry = new LogEntry(book, user, LocalDate.now(), null);
        library.getRejestrWypozyczen().add(logEntry);
    }

    public void zwrocKsiazke(Book book, User user){
        Optional<LogEntry> rejWyp = library.getRejestrWypozyczen().stream()
                .filter(wpis -> wpis.getBook().equals(book) && wpis.getUser().equals(user) && !wpis.getBook()
                        .getDostepna()).findFirst();
        if ( rejWyp.isPresent()) {
            LogEntry logEntry = rejWyp.get();
            logEntry.setDataZwrotu(LocalDate.now());
            logEntry.getBook().setDostepna(true);
            System.out.println("Book: {" + book.toString() + "} została pomyślnie zwrócona!");
        } else {
            System.out.println("WYSTĄPIL JAKIS BLAD!!!");
        }
    }

    public void wyswietlListeKsiazek() {

        for (Book book : library.getListaKsiazek()){
            System.out.println(book);
        }
    }

    public List<Book> wyszukajPoTytule(String tytul){
        BinarySearch wb = new BinarySearch(library);
        return wb.szukajTytul(tytul);

    }

    public List<Book> wyszukajPoAutorze(String imie, String nazwisko){
        BinarySearch wb = new BinarySearch(library);
        return wb.szukajAutora(imie, nazwisko);
    }

    public void  dodajUzytkownika(User user){
        library.dodajUzytkownika(user);
    }

    public void dodajUzytkownika(String imie, String nazwisko, Gender gender){
        // sprawdzenie czy uzytkownik juz istnieje?
        library.dodajUzytkownika(new User(imie, nazwisko, gender));
    }

    public void  wyswietlWypozyczoneKsiazki(){

        List<Book> wypozyczoneKsiazki = library.getRejestrWypozyczen().stream()
                .filter(wpis -> !wpis.getBook().getDostepna()).map(LogEntry::getBook)
                .collect(Collectors.toList());

        System.out.println("AKTUALNIE WYPOZYCZONE KSIAZKI:");
        for (int i = 0; i < wypozyczoneKsiazki.size(); i++) {
            System.out.println(i+". " + wypozyczoneKsiazki.get(i));
        }

    }

    public void wyswietlWypozyczonePrzezUzytkownika(User user){

        List<LogEntry> rejestr = library.getRejestrWypozyczen();
        List<Book> wypozyczoneUzytkownika = rejestr.stream().filter(wpis -> wpis.getUser()
                .equals(user) && !wpis.getBook().getDostepna())
                .map(LogEntry::getBook).collect(Collectors.toList());

        System.out.println("AKTUALNIE WYPOZYCZONE KSIAZKI PRZEZ: " + user.toString());
        for (int i = 0; i < wypozyczoneUzytkownika.size(); i++) {
            System.out.println(i+". " + wypozyczoneUzytkownika.get(i));
        }
    }
    public List<LogEntry> getWypozyczoneKsiazki() {

        return library.getRejestrWypozyczen().stream()
                .filter(wpis -> !wpis.getBook().getDostepna())
                .collect(Collectors.toList());
    }

    public List<LogEntry> getWypozyczonePrzezUzytkownika(String imie, String nazwisko){

        List<LogEntry> rejestr = library.getRejestrWypozyczen();

        List<LogEntry> wypozyczone =  rejestr.stream().filter(wpis -> wpis.getUser().getImie().equalsIgnoreCase(imie) &&
                wpis.getUser().getNazwisko().equalsIgnoreCase(nazwisko) && !wpis.getBook().getDostepna())
                .collect(Collectors.toList());

        if (wypozyczone.size() == 0 ) {
            return null;
        } else {
            return wypozyczone;
        }
    }


    public static void main(String[] args) {

        Library library = new Library();
        Backup b = new Backup();
        LibraryManagement zb = new LibraryManagement(library);

        try {
            b.odczytKsiazek(library);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        zb.dodajKsiazkeDoBiblioteki(new Book("Dziewczyna z pociągu", "Maria", "Konopnicka", 2016, Genre.THRILLER));
        zb.dodajKsiazkeDoBiblioteki(new Book("Nad Niemnem", "Eliza", "Orzeszkowa", 2016, Genre.HORROR));



    }
}
