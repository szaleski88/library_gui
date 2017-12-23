package com.sda.model;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="wpisy")
public class Registry {

    @XmlElements(@XmlElement(name="wpis"))
    private List<RegEntry> wpisy;

    public Registry(){}

    public Registry(Library b){
        this.wpisy = b.getRegistry();
    }

    public List<RegEntry> getRegEntries() {
        return wpisy;
    }

    public void setWpisy(List<RegEntry> wpisy) {
        this.wpisy = wpisy;
    }
}
