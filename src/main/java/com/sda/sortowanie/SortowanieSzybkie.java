package com.sda.sortowanie;

import com.sda.components.Ksiazka;

import java.util.ArrayList;
import java.util.List;

public class SortowanieSzybkie implements com.sda.sortowanie.Sortowanie {


    public String getNazwaAlgorytmu() {
        return "Sortowanie szybkie.";
    }


    public List<Ksiazka> sortuj(List<Ksiazka> ksiazki) {

        ksiazki = sortujRekurencyjnie(ksiazki);

        return ksiazki;
    }

    private List<Ksiazka> sortujRekurencyjnie( List<Ksiazka> lista ){

        if (lista.size() < 2 ) {
            return lista;
        } else if (lista.size() == 2 ) {
            if ( lista.get(0).compareTo(lista.get(1)) <= 0) {
                Ksiazka temp = lista.get(0);
                lista.set(0, lista.get(1));
                lista.set(1, temp);
            }
        }

        Ksiazka srodek = lista.get(0);
        List<Ksiazka> mniejszeRowne = new ArrayList<>();
        List<Ksiazka> wieksze = new ArrayList<>();
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i).compareTo(srodek) <= 0){
                mniejszeRowne.add(lista.get(i));
            }
            else {
                wieksze.add(lista.get(i));
            }
        }

        mniejszeRowne = sortujRekurencyjnie(mniejszeRowne);
        wieksze = sortujRekurencyjnie(wieksze);

        List<Ksiazka> wyniki = new ArrayList<>();
        wyniki.addAll(mniejszeRowne);
        wyniki.add(srodek);
        wyniki.addAll(wieksze);

        return wyniki;
    }

    // TESTOWANIE KODU
    public static void main(String[] args) {
        List<Ksiazka> ksiazki = new ArrayList<>();
        ksiazki.add(new Ksiazka("tytul1", "Henryk", "Sienkiewicz", 1989));
        ksiazki.add(new Ksiazka("tytul2", "Boleslaw", "Prus", 1989));
        ksiazki.add(new Ksiazka("tytul3", "Mariola", "Siekiera",  1989));
        ksiazki.add(new Ksiazka("tytul1", "Henryk",  "Walezy",1989));
        ksiazki.add(new Ksiazka("tytul4", "Adam", "Sienkiewicz",1989));
        ksiazki.add(new Ksiazka("tytul5", "Dominik", "Dobry",1989));
        ksiazki.add(new Ksiazka("tytul6", "Emil", "Czop", 1989));

        Sortowanie s = new SortowanieSzybkie();
        ksiazki = s.sortuj(ksiazki);


        for (Ksiazka ksiazka :  ksiazki) {
            System.out.println(ksiazka);
        }

    }
}
