package com.sda.model;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="wpisy")
public class Registry {

    @XmlElements(@XmlElement(name="wpis"))
    private List<LogEntry> wpisy;

    public Registry(){}

    public Registry(Library b){
        this.wpisy = b.getRegistry();
    }

    public List<LogEntry> getEntries() {
        return wpisy;
    }

    public void setWpisy(List<LogEntry> wpisy) {
        this.wpisy = wpisy;
    }
}
