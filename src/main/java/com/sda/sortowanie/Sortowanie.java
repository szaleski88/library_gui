package com.sda.sortowanie;

import com.sda.model.Ksiazka;
import com.sda.model.Uzytkownik;

import java.util.List;

public interface Sortowanie {

    String getNazwaAlgorytmu();

    List<Ksiazka> sortuj(List<Ksiazka> ksiazki);


}
