package com.sda.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ksiazki")
public class Ksiazki{

    @XmlElements(@XmlElement(name="ksiazka"))
    private List<Ksiazka> ksiazki;

    public Ksiazki(){}

    public Ksiazki(Biblioteka b){
        this.ksiazki = b.getListaKsiazek();
    }

    public List<Ksiazka> getKsiazki() {
        return ksiazki;
    }

    public void setKsiazki(List<Ksiazka> ksiazki) {
        this.ksiazki = ksiazki;
    }
}
