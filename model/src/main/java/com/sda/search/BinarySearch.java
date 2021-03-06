package com.sda.search;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.sda.controller.Backup;
import com.sda.model.Author;
import com.sda.model.Book;
import com.sda.model.Library;
import com.sda.model.User;
import com.sda.sort.QuickSort;
import com.sda.sort.Sort;

public class BinarySearch implements Search {

    /**
     * Wykorzystanie tej metody TYLKO dla posortowanych tablic
     *
     * @param liczba szukana liczba
     * @param tablica tablica elementow
     * @return indeks wejsciowej tablicy pod ktorym jest szukana liczba. -1 jesli nie znaleziono.
     */
    private List<Book> wszystkieKsiazki;
    private Library library;
    private Sort sortowanie = new QuickSort();
    private List<Book> trafienia;

    public BinarySearch(Library library) {
//        this.wszystkieKsiazki = sort.sortuj(ksiazki);
        this.library = library;
    }

    // MODUL TESTUJACY
    public static void main(String[] args) {

        Library library = new Library();
        Backup b = new Backup();

        try {
            b.readBooksFromFile(library);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(library.getAllBooks().size());
        BinarySearch wb = new BinarySearch(library);

        List<Book> ksiazki = wb.searchByTitle("Domowy Pan");
        //List<Book> autorzy = wb.szukajAutora("Adam", "Mickiewicz");

        for (Book book : ksiazki) {
            System.out.println(book);
        }

    }

    @Override
    public List<Book> searchByTitle(String tytul) {
        // ZACIĄGNIĘCIE I POSORTOWANIE AKTUALNEJ LISTY KSIĄZEK
        //this.wszystkieKsiazki = sort.sortuj(library.getAllBooks());
        this.trafienia = new ArrayList<>();
        this.wszystkieKsiazki = library.getAllBooks();

        this.wszystkieKsiazki.sort((a, b) -> {
            String tytul1 = a.getTitle();
            String tytul2 = b.getTitle();
            return tytul1.compareToIgnoreCase(tytul2);
        });

        int pierwszyTraf = szukajTytuluRekurencyjnie(tytul, wszystkieKsiazki);
        if (pierwszyTraf == -1) return trafienia;

        trafienia.add(wszystkieKsiazki.get(pierwszyTraf));
        for (int i = pierwszyTraf + 1; i < wszystkieKsiazki.size(); i++) {
            if (wszystkieKsiazki.get(i).getTitle().equalsIgnoreCase(tytul)) trafienia.add(wszystkieKsiazki.get(i));
            else break;
        }

        for (int i = pierwszyTraf - 1; i >= 0; i--) {
            if (wszystkieKsiazki.get(i).getTitle().equalsIgnoreCase(tytul)) trafienia.add(wszystkieKsiazki.get(i));
            else break;
        }
        return trafienia;
    }

    @Override
    public List<Book> szukajAutora(String imie, String nazwisko) {
        // ZACIĄGNIĘCIE I POSORTOWANIE AKTUALNEJ LISTY KSIĄZEK
        this.trafienia = new ArrayList<>();
        Author author = new Author(imie, nazwisko);
        this.wszystkieKsiazki = sortowanie.sortuj(library.getAllBooks());
        int pierwszyTraf = szukajAutoraRekurencyjnie(author, wszystkieKsiazki);
        if (pierwszyTraf == -1) return trafienia;

        trafienia.add(wszystkieKsiazki.get(pierwszyTraf));
        for (int i = pierwszyTraf + 1; i < wszystkieKsiazki.size(); i++) {
            if (wszystkieKsiazki.get(i).getAuthor().equals(author)) trafienia.add(wszystkieKsiazki.get(i));
            else break;
        }

        for (int i = pierwszyTraf - 1; i >= 0; i--) {
            if (wszystkieKsiazki.get(i).getAuthor().equals(author)) trafienia.add(wszystkieKsiazki.get(i));
            else break;
        }
        return trafienia;
    }

    private int szukajAutoraRekurencyjnie(Author author, List<Book> ksiazki) {
        if (ksiazki == null || ksiazki.size() == 0) return -1;
        int przesuniecie = 0;
        int szukanaLiczba;
        int index = ksiazki.size() / 2;

        if (ksiazki.get(index).getAuthor().equals(author)) {
            return index;
        } else if (ksiazki.get(index).getAuthor().compareTo(author) > 0) {
            List<Book> lewa = new ArrayList<>();

            for (int i = 0; i < index; i++) {
                lewa.add(i, ksiazki.get(i));
            }

            szukanaLiczba = szukajAutoraRekurencyjnie(author, lewa);
        } else {
            List<Book> prawa = new ArrayList<>();

            // NOWOSC!
            for (int i = index + 1, j = 0; i < ksiazki.size(); i++, j++) {
                prawa.add(j, ksiazki.get(i));
            }
            przesuniecie = index + 1;
            szukanaLiczba = szukajAutoraRekurencyjnie(author, prawa);
            if (szukanaLiczba != -1) {
                szukanaLiczba += przesuniecie;
            }
        }
        return szukanaLiczba;
    }

    private int szukajTytuluRekurencyjnie(String tytul, List<Book> ksiazki) {

        if (ksiazki == null || ksiazki.size() == 0) return -1;
        int przesuniecie = 0;
        int szukanaLiczba;
        int index = ksiazki.size() / 2;

        if (ksiazki.get(index).getTitle().equalsIgnoreCase(tytul)) {
            return index;
        } else if (ksiazki.get(index).getTitle().compareToIgnoreCase(tytul) > 0) {
            List<Book> lewa = new ArrayList<>();

            for (int i = 0; i < index; i++) {
                lewa.add(i, ksiazki.get(i));
            }

            szukanaLiczba = szukajTytuluRekurencyjnie(tytul, lewa);
        } else {
            List<Book> prawa = new ArrayList<>();

            // NOWOSC!
            for (int i = index + 1, j = 0; i < ksiazki.size(); i++, j++) {
                prawa.add(j, ksiazki.get(i));
            }
            przesuniecie = index + 1;
            szukanaLiczba = szukajTytuluRekurencyjnie(tytul, prawa);
            if (szukanaLiczba != -1) {
                szukanaLiczba += przesuniecie;
            }
        }
        return szukanaLiczba;
    }

    public List<User> searchForUser(String imie, String nazwisko) {
        List<User> uzytkownicy = library.getUsersList();
        List<User> znalezieni = new ArrayList<>();

        uzytkownicy.sort((o1, o2) -> {
            if (o1.equals(o2)) return 0;
            if (o1.getFirstName().equalsIgnoreCase(o2.getFirstName()) && o1.getLastName().equalsIgnoreCase(o2.getLastName())) {
                return 0;
            } else if (o1.getFirstName().compareToIgnoreCase(o2.getFirstName()) == 0) {
                return o1.getLastName().compareToIgnoreCase(o2.getLastName());
            } else return o1.getFirstName().compareTo(o2.getFirstName());
        });

        int pierwszyTraf = szukajUzytkownikaRekurencyjnie(imie, nazwisko, uzytkownicy);
        if (pierwszyTraf == -1) return znalezieni;

        znalezieni.add(uzytkownicy.get(pierwszyTraf));
        for (int i = pierwszyTraf + 1; i < uzytkownicy.size(); i++) {
            if (uzytkownicy.get(i).getFirstName().equalsIgnoreCase(imie) &&
                uzytkownicy.get(i).getLastName().equalsIgnoreCase(nazwisko)) znalezieni.add(uzytkownicy.get(i));
            else break;
        }

        for (int i = pierwszyTraf - 1; i >= 0; i--) {
            if (uzytkownicy.get(i).getFirstName().equalsIgnoreCase(imie) &&
                uzytkownicy.get(i).getLastName().equalsIgnoreCase(nazwisko)) znalezieni.add(uzytkownicy.get(i));
            else break;
        }
        return znalezieni;

    }

    private int szukajUzytkownikaRekurencyjnie(String imie, String nazwisko, List<User> uzytkownicy) {

        if (uzytkownicy == null || uzytkownicy.size() == 0) return -1;
        int przesuniecie = 0;
        int szukanaLiczba;
        int index = uzytkownicy.size() / 2;

        if (uzytkownicy.get(index).getFirstName().equalsIgnoreCase(imie)
            && uzytkownicy.get(index).getLastName().equalsIgnoreCase(nazwisko)) {
            return index;
        } else if (uzytkownicy.get(index).getFirstName().compareToIgnoreCase(imie) > 0) {
            List<User> lewa = new ArrayList<>();

            for (int i = 0; i < index; i++) {
                lewa.add(i, uzytkownicy.get(i));
            }

            szukanaLiczba = szukajUzytkownikaRekurencyjnie(imie, nazwisko, lewa);
        } else {
            List<User> prawa = new ArrayList<>();

            // NOWOSC!
            for (int i = index + 1, j = 0; i < uzytkownicy.size(); i++, j++) {
                prawa.add(j, uzytkownicy.get(i));
            }
            przesuniecie = index + 1;
            szukanaLiczba = szukajUzytkownikaRekurencyjnie(imie, nazwisko, prawa);
            if (szukanaLiczba != -1) {
                szukanaLiczba += przesuniecie;
            }
        }
        return szukanaLiczba;
    }
}
