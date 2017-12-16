package com.sda.wyszukiwanie;

import com.sda.model.Autor;
import com.sda.model.Ksiazka;
import com.sda.model.Uzytkownik;

import java.util.List;

public interface Wyszukiwanie {

    /**
     * Metody szukaja WSZYSTKICH ksiazek o podanym tytule lub danego autora
     * Jeśli znajdzie -> zwraca LISTĘ wszystkich ksiązek
     * jeśli nie - zwraca -1
     * @return
     */
    List<Ksiazka> szukajTytul(String tytul);
    List<Ksiazka> szukajAutora(String imie, String nazwisko);
    List<Uzytkownik> szukajUzytkownika(String imie, String nazwisko);

}
