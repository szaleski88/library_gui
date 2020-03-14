package com.sda.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "allUsers")
public class AllUsers {

    @XmlElements(@XmlElement(name = "user"))
    private List<User> allUsers;

    public AllUsers() {
    }

    public AllUsers(Library b) {
        this.allUsers = b.getUsersList();
    }

    public List<User> getUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

}