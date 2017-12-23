package com.sda.search;

import com.sda.model.Book;
import com.sda.model.User;

import java.util.List;

public interface Search {

    /**
     * Metody szukaja WSZYSTKICH ksiazek o podanym tytule lub danego autora
     * Jeśli znajdzie -> zwraca LISTĘ wszystkich ksiązek
     * jeśli nie - zwraca -1
     * @return
     */
    List<Book> searchByTitle(String tytul);
    List<Book> szukajAutora(String imie, String nazwisko);
    List<User> szukajUzytkownika(String imie, String nazwisko);

}
