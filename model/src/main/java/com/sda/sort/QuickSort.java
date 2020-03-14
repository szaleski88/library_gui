package com.sda.sort;

import java.util.ArrayList;
import java.util.List;

import com.sda.model.Book;

public class QuickSort implements Sort {

    public String getNazwaAlgorytmu() {
        return "Sort szybkie.";
    }

    public List<Book> sortuj(List<Book> ksiazki) {

        ksiazki = sortujRekurencyjnie(ksiazki);

        return ksiazki;
    }

    private List<Book> sortujRekurencyjnie(List<Book> lista) {

        if (lista.size() < 2) {
            return lista;
        } else if (lista.size() == 2) {
            if (lista.get(0).compareTo(lista.get(1)) <= 0) {
                Book temp = lista.get(0);
                lista.set(0, lista.get(1));
                lista.set(1, temp);
            }
        }

        Book srodek = lista.get(0);
        List<Book> mniejszeRowne = new ArrayList<>();
        List<Book> wieksze = new ArrayList<>();
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i).compareTo(srodek) <= 0) {
                mniejszeRowne.add(lista.get(i));
            } else {
                wieksze.add(lista.get(i));
            }
        }

        mniejszeRowne = sortujRekurencyjnie(mniejszeRowne);
        wieksze = sortujRekurencyjnie(wieksze);

        List<Book> wyniki = new ArrayList<>();
        wyniki.addAll(mniejszeRowne);
        wyniki.add(srodek);
        wyniki.addAll(wieksze);

        return wyniki;
    }

}
