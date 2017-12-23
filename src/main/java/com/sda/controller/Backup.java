package com.sda.controller;


import com.sda.model.*;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;


public class Backup {


    public void zapisUzytkownikow(Library library) {

        AllUsers allUsers = new AllUsers(library);

        try {
            zapisDoPliku(allUsers);
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }

    public void zapisRejestru(Library library) {
        LogEntries logEntries = new LogEntries(library);

        try {
            zapisDoPliku(logEntries);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void zapisKsiazek(Library library) {
        AllBooks allBooks = new AllBooks(library);

        try {
            zapisDoPliku(allBooks);
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

    public void odczytUzytkownikow(Library library) throws JAXBException {

        File file = new File("./allUsers.xml");
        createFile(file);

        JAXBContext jaxbContext = JAXBContext.newInstance(AllUsers.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        AllUsers allUsers = (AllUsers) jaxbUnmarshaller.unmarshal(file);

        for (User uz : allUsers.getUzytkownicy()) {
            library.dodajUzytkownika(uz);
        }
    }

    public void odczytKsiazek(Library library) throws JAXBException {

        File file = new File ("./allBooks.xml");
        createFile(file);

        JAXBContext jaxbContext = JAXBContext.newInstance(AllBooks.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        AllBooks allBooks = (AllBooks) jaxbUnmarshaller.unmarshal(file);

        for (Book ks : allBooks.getKsiazki()) {
            library.dodajKsiazke(ks);
        }


    }

    public void odczytRejestru(Library library) throws JAXBException {

        File file = new File("./logEntries.xml");
        createFile(file);
        JAXBContext jaxbContext = JAXBContext.newInstance(LogEntries.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        LogEntries logEntries = (LogEntries) jaxbUnmarshaller.unmarshal(file);
        if (logEntries.getWpisy().size() >  0) {
            for (LogEntry wp : logEntries.getWpisy()) {
                library.dodajWpis(wp);
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

        Library library = new Library();

            // wyświetlenie na out

        Backup b = new Backup();


//        library.dodajUzytkownika(new User("Stefan", "Mikrut", Gender.MEZCZYZNA));
//        library.dodajUzytkownika(new User("Mariola", "Kowalska", Gender.KOBIETA));
//        library.dodajUzytkownika(new User("Krzysztof", "Różalski", Gender.MEZCZYZNA));
//        library.dodajUzytkownika(new User("Katarzyna", "Kowalec", Gender.KOBIETA));
//
//        b.zapisUzytkownikow(library);
//
//
        b.odczytKsiazek(library);
        b.odczytUzytkownikow(library);
        LibraryManagement zb = new LibraryManagement(library);
        User user = new User("Sebastian", "Zaleski", Gender.MEZCZYZNA);
        library.dodajUzytkownika(user);

        zb.wypozyczKsiazke(library.getListaKsiazek().get(44), user);
        zb.wypozyczKsiazke(library.getListaKsiazek().get(57), user);
        zb.wypozyczKsiazke(library.getListaKsiazek().get(111), user);
        zb.wypozyczKsiazke(library.getListaKsiazek().get(178), user);
        zb.wypozyczKsiazke(library.getListaKsiazek().get(11), library.getListaUzytkownikow().get(0));
        zb.wypozyczKsiazke(library.getListaKsiazek().get(17), library.getListaUzytkownikow().get(0));
        zb.wypozyczKsiazke(library.getListaKsiazek().get(100), library.getListaUzytkownikow().get(2));
        zb.wypozyczKsiazke(library.getListaKsiazek().get(101), library.getListaUzytkownikow().get(3));

        zb.wyswietlWypozyczoneKsiazki();
        b.zapisUzytkownikow(library);
        b.zapisRejestru(library);





    }

}
