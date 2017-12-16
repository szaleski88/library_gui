package com.sda.model;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="uzytkownicy")
public class Uzytkownicy{
    @XmlElements(@XmlElement( name="uzytkownik"))
    private List<Uzytkownik> uzytkownicy;

    public Uzytkownicy(){
    }

    public Uzytkownicy(Biblioteka b){
        this.uzytkownicy = b.getListaUzytkownikow();
    }

    public List<Uzytkownik> getUzytkownicy() {
        return uzytkownicy;
    }

    public void setUzytkownicy(List<Uzytkownik> uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }


}