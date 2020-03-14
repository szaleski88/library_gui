package com.sda.model;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
public class User implements Comparable<User> {

    @XmlElement(name = "firstName", required = true)
    private String firstName;
    @XmlElement(name = "lastName", required = true)
    private String lastName;
    @XmlElement(name = "id", required = true)
    private String id;
    @XmlElement(name = "gender", required = true)
    private Gender gender;

    //    private Adres adres;
    public User() {

    }

    public User(String firstName, String lastName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        id = UUID.randomUUID().toString();
    }

    public User(String firstName, String lastName, String id, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(firstName);
        sb.append(" ").append(lastName);
        return sb.toString();
    }

    public int compareTo(User o) {
        if (this.equals(o)) return 0;
        if (this.firstName.equalsIgnoreCase(o.getFirstName()) && this.getLastName().equalsIgnoreCase(o.getLastName())) {
            return 0;
        } else if (this.firstName.compareToIgnoreCase(o.getFirstName()) == 0) {
            return this.getLastName().compareToIgnoreCase(o.getLastName());
        } else return this.getFirstName().compareTo(o.getFirstName());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (!firstName.equals(that.firstName)) return false;
        return !lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
//        result = 31 * result + adres.hashCode();
        return result;
    }
}
