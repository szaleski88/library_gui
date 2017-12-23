package com.sda.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="authorr")
public class Author implements Comparable<Author>{

    @XmlElement(name = "firstName")
    private String firstName;
    @XmlElement(name = "lastName")
    private String lastName;

    public Author(){}

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(firstName);
        sb.append(" ").append(lastName);
        return sb.toString();
    }

    @Override
    public int compareTo(Author o) {
        if (this.equals(o)) return 0;
        if (this.getFirstName().equalsIgnoreCase(o.getFirstName())) {
            return this.getLastName().compareToIgnoreCase(o.getLastName());
        }
        return this.getFirstName().compareToIgnoreCase(o.getFirstName());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (!firstName.equalsIgnoreCase(author.firstName)) return false;
        return lastName.equalsIgnoreCase(author.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }
}
