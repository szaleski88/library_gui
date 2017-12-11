package com.sda.model;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="uzytkownicy")
public class Uzytkownicy{
    @XmlElements(@XmlElement( name="uzytkownik"))
    private List<Uzytkownik> Uzytkownicy;

    public Uzytkownicy(){
    }

    public Uzytkownicy(Biblioteka b){
        this.Uzytkownicy = b.getListaUzytkownikow();
    }

    public List<Uzytkownik> getUzytkownicy() {
        return Uzytkownicy;
    }

    public void setUzytkownicy(List<Uzytkownik> uzytkownicy) {
        this.Uzytkownicy = uzytkownicy;
    }
}