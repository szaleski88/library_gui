package com.sda.model;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="uzytkownicy")
public class AllUsers {
    @XmlElements(@XmlElement( name="uzytkownik"))
    private List<User> uzytkownicy;

    public AllUsers(){
    }

    public AllUsers(Library b){
        this.uzytkownicy = b.getListaUzytkownikow();
    }

    public List<User> getUsers() {
        return uzytkownicy;
    }

    public void setUzytkownicy(List<User> uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }


}