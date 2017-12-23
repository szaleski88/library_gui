package com.sda.sort;

import com.sda.model.Book;

import java.util.List;

public interface Sort {

    String getNazwaAlgorytmu();

    List<Book> sortuj(List<Book> ksiazki);


}
