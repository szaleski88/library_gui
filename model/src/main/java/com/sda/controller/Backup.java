package com.sda.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sda.model.AllBooks;
import com.sda.model.AllUsers;
import com.sda.model.Book;
import com.sda.model.Library;
import com.sda.model.RegEntry;
import com.sda.model.Registry;
import com.sda.model.User;

public class Backup {

    public static void readUsersFromFile(Library library) throws JAXBException {

        File file = new File("./allUsers.xml");
        createFile(file);

        JAXBContext jaxbContext = JAXBContext.newInstance(AllUsers.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        AllUsers allUsers = (AllUsers) jaxbUnmarshaller.unmarshal(file);

        for (User us : allUsers.getUsers()) {
            library.addUser(us);
        }
    }

    public static void readBooksFromFile(Library library) throws JAXBException {

        File file = new File("./allBooks.xml");
        createFile(file);

        JAXBContext jaxbContext = JAXBContext.newInstance(AllBooks.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        AllBooks allBooks = (AllBooks) jaxbUnmarshaller.unmarshal(file);

        for (Book book : allBooks.getBooks()) {
            library.addBook(book);
        }

    }

    public static void readRegistryFromFile(Library library) throws JAXBException {

        File file = new File("./registry.xml");
        createFile(file);
        JAXBContext jaxbContext = JAXBContext.newInstance(Registry.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Registry registry = (Registry) jaxbUnmarshaller.unmarshal(file);
        if (registry.getRegEntries().size() > 0) {
            for (RegEntry le : registry.getRegEntries()) {
                library.addRegEntry(le);
            }
        }
    }

    private static void createFile(File file) {
        if (!file.exists())
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void saveUsersToFile(Library library) {

        AllUsers allUsers = new AllUsers(library);

        try {
            saveToFile(allUsers);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void saveRegistryToFile(Library library) {
        Registry registry = new Registry(library);

        try {
            saveToFile(registry);
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

    private void saveToFile(Object o) throws JAXBException {
        String[] parts = o.getClass().toString().toLowerCase().split("\\.");

        File file = new File(String.format(".\\%s.xml", parts[parts.length - 1]));
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(o, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
