package com.sda.wyszukiwanie;

import java.util.List;

public interface Wyszukiwanie {

    /**
     * Metoda szuka pierwszego wystąpienia liczby w tablica,
     * Jeśli go znajdzie -> zwraca indeks tego elementu,
     * jeśli nie - zwraca -1
     * @param liczba
     * @param tablica
     * @return
     */
    int szukaj(int liczba, int[] tablica);

    /**
     * Metoda szuka pierwszego wystąpienia liczby w tablica,
     * Jeśli go znajdzie -> zwraca indeks tego elementu,
     * jeśli nie - zwraca -1
     * @param liczba
     * @return
     */
    int szukaj(int liczba);

    int getLicznikWywolan();

    String getNazwaWyszukiwarki();

    /**
     * Metoda szuka WSZYSTKICH wystąpien liczby w tablicy,
     * Jeśli go znajdzie -> zwraca indeks tego elementu,
     * jeśli nie - zwraca -1
     * @param liczba
     * @return
     */
    List<Integer> szukajWszystkie(int liczba);

}
