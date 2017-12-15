package com.sda.model;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="wpisy")
public class Wpisy{

    @XmlElements(@XmlElement(name="wpis"))
    private List<Wpis> wpisy;

    public Wpisy(){}

    public Wpisy(Biblioteka b){
        this.wpisy = b.getRejestrWypozyczen();
    }

    public List<Wpis> getWpisy() {
        return wpisy;
    }

    public void setWpisy(List<Wpis> wpisy) {
        this.wpisy = wpisy;
    }
}
