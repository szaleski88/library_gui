package com.sda.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "registry")
public class Registry {

    @XmlElements(@XmlElement(name = "regEntry"))
    private List<RegEntry> regEntries;

    public Registry() {
    }

    public Registry(Library b) {
        this.regEntries = b.getRegistry();
    }

    public List<RegEntry> getRegEntries() {
        return regEntries;
    }

    public void setRegEntries(List<RegEntry> regEntries) {
        this.regEntries = regEntries;
    }
}
