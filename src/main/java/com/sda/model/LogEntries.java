package com.sda.model;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="wpisy")
public class LogEntries {

    @XmlElements(@XmlElement(name="wpis"))
    private List<LogEntry> wpisy;

    public LogEntries(){}

    public LogEntries(Library b){
        this.wpisy = b.getRejestrWypozyczen();
    }

    public List<LogEntry> getEntries() {
        return wpisy;
    }

    public void setWpisy(List<LogEntry> wpisy) {
        this.wpisy = wpisy;
    }
}
