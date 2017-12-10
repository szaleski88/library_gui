package com.sda.sortowanie;

import com.sda.components.Ksiazka;

import java.util.List;

public interface Sortowanie {

    String getNazwaAlgorytmu();

    List<Ksiazka> sortuj(List<Ksiazka> ksiazki);


}
