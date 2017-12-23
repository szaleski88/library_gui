package com.sda.model;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="registry")
public class Registry {

    @XmlElements(@XmlElement(name="regEntry"))
    private List<RegEntry> regEntries;

    public Registry(){}

    public Registry(Library b){
        this.regEntries = b.getRegistry();
    }

    public List<RegEntry> getRegEntries() {
        return regEntries;
    }

    public void setRegEntries(List<RegEntry> regEntries) {
        this.regEntries = regEntries;
    }
}
