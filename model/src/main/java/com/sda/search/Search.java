package com.sda.search;

import java.util.List;

import com.sda.model.Book;
import com.sda.model.User;

public interface Search {

    /**
     * Metody szukaja WSZYSTKICH ksiazek o podanym tytule lub danego autora
     * Jeśli znajdzie -> zwraca LISTĘ wszystkich ksiązek
     * jeśli nie - zwraca -1
     *
     * @return
     */
    List<Book> searchByTitle(String tytul);

    List<Book> szukajAutora(String imie, String nazwisko);

    List<User> searchForUser(String imie, String nazwisko);

}
