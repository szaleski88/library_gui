package com.sda.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ksiazki")
public class Ksiazki{

    @XmlElements(@XmlElement(name="ksiazka"))
    private List<Ksiazka> Ksiazki;

    public Ksiazki(){}

    public Ksiazki(Biblioteka b){
        this.Ksiazki = b.getListaKsiazek();
    }

    public List<Ksiazka> getKsiazki() {
        return Ksiazki;
    }

    public void setKsiazki(List<Ksiazka> ksiazki) {
        this.Ksiazki = ksiazki;
    }
}
