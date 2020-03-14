package com.sda.sort;

import java.util.List;

import com.sda.model.Book;

public interface Sort {

    String getNazwaAlgorytmu();

    List<Book> sortuj(List<Book> ksiazki);

}
