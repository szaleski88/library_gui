package com.sda.controller;


import com.sda.exceptions.ReadFileException;
import com.sda.model.*;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Backup {


    public void zapisUzytkownikow(Biblioteka biblioteka) {

        Uzytkownicy uzytkownicy = new Uzytkownicy(biblioteka);

        try {
            zapisDoPliku(uzytkownicy);
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }

    public void zapisRejestru(Biblioteka biblioteka) {
        Wpisy wpisy = new Wpisy(biblioteka);

        try {
            zapisDoPliku(wpisy);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void zapisKsiazek(Biblioteka biblioteka) {
        Ksiazki ksiazki = new Ksiazki(biblioteka);

        try {
            zapisDoPliku(ksiazki);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void zapisDoPliku(Object o ) throws JAXBException{
        String[] parts = o.getClass().toString().toLowerCase().split("\\.");

        File file = new File (String.format(".\\%s.xml", parts[parts.length-1] ));
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(o, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void odczytUzytkownikow(Biblioteka biblioteka) throws JAXBException {

        File file = new File("./uzytkownicy.xml");
        if(!file.exists())
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        JAXBContext jaxbContext = JAXBContext.newInstance(Uzytkownicy.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Uzytkownicy uzytkownicy = (Uzytkownicy) jaxbUnmarshaller.unmarshal(file);

        for (Uzytkownik uz : uzytkownicy.getUzytkownicy()) {
            biblioteka.dodajUzytkownika(uz);
        }
    }

    public void odczytKsiazek(Biblioteka biblioteka) throws JAXBException {

        File file = new File ("./ksiazki.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Ksiazki.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Ksiazki ksiazki = (Ksiazki) jaxbUnmarshaller.unmarshal(file);

        for (Ksiazka ks : ksiazki.getKsiazki()) {
            biblioteka.dodajKsiazke(ks);
        }


    }

    public void odczytRejestru(Biblioteka biblioteka) throws JAXBException {

        File file = new File ("./wpisy.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Wpisy.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Wpisy wpisy = (Wpisy) jaxbUnmarshaller.unmarshal(file);

        for (Wpis wp : wpisy.getWpisy()) {
            biblioteka.dodajWpis(wp);
        }


    }
    public static void main(String[] args) throws JAXBException {

        Biblioteka biblioteka = new Biblioteka();

            // wyświetlenie na out

        Backup b = new Backup();

        /*
        biblioteka.dodajUzytkownika(new Uzytkownik("Stefan", "Mikrut", Plec.MEZCZYZNA));
        biblioteka.dodajUzytkownika(new Uzytkownik("Mariola", "Kowalska", Plec.KOBIETA));
        biblioteka.dodajUzytkownika(new Uzytkownik("Krzysztof", "Różalski", Plec.MEZCZYZNA));
        biblioteka.dodajUzytkownika(new Uzytkownik("Katarzyna", "Kowalec", Plec.KOBIETA));

        b.zapisUzytkownikow(biblioteka);
        */

        b.odczytKsiazek(biblioteka);
        b.odczytUzytkownikow(biblioteka);
        ZarzadzanieBiblioteka zb = new ZarzadzanieBiblioteka(biblioteka);

        zb.wypozyczKsiazke(biblioteka.getListaKsiazek().get(12), biblioteka.getListaUzytkownikow().get(1));
        zb.wypozyczKsiazke(biblioteka.getListaKsiazek().get(112), biblioteka.getListaUzytkownikow().get(2));
        zb.wypozyczKsiazke(biblioteka.getListaKsiazek().get(132), biblioteka.getListaUzytkownikow().get(0));
        zb.wypozyczKsiazke(biblioteka.getListaKsiazek().get(2), biblioteka.getListaUzytkownikow().get(3));

        zb.wyswietlWypozyczoneKsiazki();





    }

}
