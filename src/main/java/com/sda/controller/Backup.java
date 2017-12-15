package com.sda.controller;


import com.sda.exceptions.ReadFileException;
import com.sda.model.*;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
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
        createFile(file);

        JAXBContext jaxbContext = JAXBContext.newInstance(Uzytkownicy.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Uzytkownicy uzytkownicy = (Uzytkownicy) jaxbUnmarshaller.unmarshal(file);

        for (Uzytkownik uz : uzytkownicy.getUzytkownicy()) {
            biblioteka.dodajUzytkownika(uz);
        }
    }

    public void odczytKsiazek(Biblioteka biblioteka) throws JAXBException {

        File file = new File ("./ksiazki.xml");
        createFile(file);

        JAXBContext jaxbContext = JAXBContext.newInstance(Ksiazki.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Ksiazki ksiazki = (Ksiazki) jaxbUnmarshaller.unmarshal(file);

        for (Ksiazka ks : ksiazki.getKsiazki()) {
            biblioteka.dodajKsiazke(ks);
        }


    }

    public void odczytRejestru(Biblioteka biblioteka) throws JAXBException {

        File file = new File("./wpisy.xml");
        createFile(file);
        JAXBContext jaxbContext = JAXBContext.newInstance(Wpisy.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Wpisy wpisy = (Wpisy) jaxbUnmarshaller.unmarshal(file);
        if (wpisy.getWpisy().size() >  0) {
            for (Wpis wp : wpisy.getWpisy()) {
                biblioteka.dodajWpis(wp);
            }
        }
    }

    private void createFile(File file){
        if(!file.exists())
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    public static void main(String[] args) throws JAXBException {

        Biblioteka biblioteka = new Biblioteka();

            // wyświetlenie na out

        Backup b = new Backup();


//        biblioteka.dodajUzytkownika(new Uzytkownik("Stefan", "Mikrut", Plec.MEZCZYZNA));
//        biblioteka.dodajUzytkownika(new Uzytkownik("Mariola", "Kowalska", Plec.KOBIETA));
//        biblioteka.dodajUzytkownika(new Uzytkownik("Krzysztof", "Różalski", Plec.MEZCZYZNA));
//        biblioteka.dodajUzytkownika(new Uzytkownik("Katarzyna", "Kowalec", Plec.KOBIETA));
//
//        b.zapisUzytkownikow(biblioteka);
//
//
        b.odczytKsiazek(biblioteka);
        b.odczytUzytkownikow(biblioteka);
        ZarzadzanieBiblioteka zb = new ZarzadzanieBiblioteka(biblioteka);
        Uzytkownik uzytkownik = new Uzytkownik("Sebastian", "Zaleski", Plec.MEZCZYZNA);
        biblioteka.dodajUzytkownika(uzytkownik);

        zb.wypozyczKsiazke(biblioteka.getListaKsiazek().get(44), uzytkownik);
        zb.wypozyczKsiazke(biblioteka.getListaKsiazek().get(57), uzytkownik);
        zb.wypozyczKsiazke(biblioteka.getListaKsiazek().get(111), uzytkownik);
        zb.wypozyczKsiazke(biblioteka.getListaKsiazek().get(178), uzytkownik);

        zb.wyswietlWypozyczoneKsiazki();
        b.zapisUzytkownikow(biblioteka);
        b.zapisRejestru(biblioteka);





    }

}
