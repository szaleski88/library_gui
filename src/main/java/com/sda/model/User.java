package com.sda.model;

import javax.xml.bind.annotation.*;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "uzytkownik")
public class User implements  Comparable<User>{

    @XmlElement(name = "imie", required = true)
    private String imie;
    @XmlElement(name = "nazwisko", required = true)
    private String nazwisko;
    @XmlElement(name = "id", required = true)
    private String id;
    @XmlElement(name = "gender", required = true)
    private Gender gender;
//    private Adres adres;
    public User(){

    }


    public User(String imie, String nazwisko, Gender gender) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.gender = gender;
        id = UUID.randomUUID().toString();
    }

    public User(String imie, String nazwisko, String id, Gender gender) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.id = id;
        this.gender = gender;
    }

    public String getImie() {
        return imie;
    }

    public String getLastName() {
        return nazwisko;
    }

    public Gender getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(imie);
        sb.append(" ").append(nazwisko);
        return sb.toString();
    }

    public int compareTo(User o) {
        if (this.equals(o) ) return 0;
        if (this.imie.equalsIgnoreCase(o.getImie())&& this.getLastName().equalsIgnoreCase(o.getLastName())) {
            return 0;
        }

        else if ( this.imie.compareToIgnoreCase(o.getImie()) == 0 ) {
            return this.getLastName().compareToIgnoreCase(o.getLastName());
        }
        else return this.getImie().compareTo(o.getImie());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (!imie.equals(that.imie)) return false;
        return !nazwisko.equals(that.nazwisko);
    }

    @Override
    public int hashCode() {
        int result = imie.hashCode();
        result = 31 * result + nazwisko.hashCode();
//        result = 31 * result + adres.hashCode();
        return result;
    }
}
