package com.sda.model;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="allUsers")
public class AllUsers {
    @XmlElements(@XmlElement( name="user"))
    private List<User> allUsers;

    public AllUsers(){
    }

    public AllUsers(Library b){
        this.allUsers = b.getUsersList();
    }

    public List<User> getUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }


}