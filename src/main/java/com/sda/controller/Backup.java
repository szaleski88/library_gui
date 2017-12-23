package com.sda.controller;


import com.sda.model.*;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;


public class Backup {


    public void saveUsersToFile(Library library) {

        AllUsers allUsers = new AllUsers(library);

        try {
            saveToFile(allUsers);
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }

    public void saveLogsToFile(Library library) {
        LogEntries logEntries = new LogEntries(library);

        try {
            saveToFile(logEntries);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void saveBooksToFile(Library library) {
        AllBooks allBooks = new AllBooks(library);

        try {
            saveToFile(allBooks);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(Object o ) throws JAXBException{
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

    public void readUsersFromFile(Library library) throws JAXBException {

        File file = new File("./allUsers.xml");
        createFile(file);

        JAXBContext jaxbContext = JAXBContext.newInstance(AllUsers.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        AllUsers allUsers = (AllUsers) jaxbUnmarshaller.unmarshal(file);

        for (User us : allUsers.getUsers()) {
            library.addUser(us);
        }
    }

    public void readBooksFromFile(Library library) throws JAXBException {

        File file = new File ("./allBooks.xml");
        createFile(file);

        JAXBContext jaxbContext = JAXBContext.newInstance(AllBooks.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        AllBooks allBooks = (AllBooks) jaxbUnmarshaller.unmarshal(file);

        for (Book book : allBooks.getBooks()) {
            library.addBook(book);
        }


    }

    public void readLogsFromFile(Library library) throws JAXBException {

        File file = new File("./logEntries.xml");
        createFile(file);
        JAXBContext jaxbContext = JAXBContext.newInstance(LogEntries.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        LogEntries logEntries = (LogEntries) jaxbUnmarshaller.unmarshal(file);
        if (logEntries.getEntries().size() >  0) {
            for (LogEntry le : logEntries.getEntries()) {
                library.addEntry(le);
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


//        library.addUser(new User("Stefan", "Mikrut", Gender.MEZCZYZNA));
//        library.addUser(new User("Mariola", "Kowalska", Gender.KOBIETA));
//        library.addUser(new User("Krzysztof", "Różalski", Gender.MEZCZYZNA));
//        library.addUser(new User("Katarzyna", "Kowalec", Gender.KOBIETA));
//
//        b.saveUsersToFile(library);
//
//
        b.readBooksFromFile(library);
        b.readUsersFromFile(library);
        LibraryManagement zb = new LibraryManagement(library);
        User user = new User("Sebastian", "Zaleski", Gender.MEZCZYZNA);
        library.addUser(user);

        zb.wypozyczKsiazke(library.getListaKsiazek().get(44), user);
        zb.wypozyczKsiazke(library.getListaKsiazek().get(57), user);
        zb.wypozyczKsiazke(library.getListaKsiazek().get(111), user);
        zb.wypozyczKsiazke(library.getListaKsiazek().get(178), user);
        zb.wypozyczKsiazke(library.getListaKsiazek().get(11), library.getListaUzytkownikow().get(0));
        zb.wypozyczKsiazke(library.getListaKsiazek().get(17), library.getListaUzytkownikow().get(0));
        zb.wypozyczKsiazke(library.getListaKsiazek().get(100), library.getListaUzytkownikow().get(2));
        zb.wypozyczKsiazke(library.getListaKsiazek().get(101), library.getListaUzytkownikow().get(3));

        zb.wyswietlWypozyczoneKsiazki();
        b.saveUsersToFile(library);
        b.saveLogsToFile(library);





    }

}
