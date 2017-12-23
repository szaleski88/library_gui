package com.sda.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ksiazki")
public class AllBooks {

    @XmlElements(@XmlElement(name="ksiazka"))
    private List<Book> ksiazki;

    public AllBooks(){}

    public AllBooks(Library b){
        this.ksiazki = b.getListaKsiazek();
    }

    public List<Book> getBooks() {
        return ksiazki;
    }

    public void setKsiazki(List<Book> ksiazki) {
        this.ksiazki = ksiazki;
    }
}
