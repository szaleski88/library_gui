package com.sda.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="allBooks")
public class AllBooks {

    @XmlElements(@XmlElement(name="book"))
    private List<Book> books;

    public AllBooks(){}

    public AllBooks(Library b){
        this.books = b.getAllBooks();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
