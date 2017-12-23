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

    private void saveToFile(Object o ) throws JAXBException{
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

    public void readRegistryFromFile(Library library) throws JAXBException {

        File file = new File("./registry.xml");
        createFile(file);
        JAXBContext jaxbContext = JAXBContext.newInstance(Registry.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Registry registry = (Registry) jaxbUnmarshaller.unmarshal(file);
        if (registry.getRegEntries().size() >  0) {
            for (RegEntry le : registry.getRegEntries()) {
                library.addRegEntry(le);
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


        library.addUser(new User("Jacob", "Moore", Gender.MALE));
        library.addUser(new User("Elizabeth", "Hurley", Gender.FEMALE));
        library.addUser(new User("Cristopher", "Doe", Gender.MALE));
        library.addUser(new User("Catrine", "Cooper", Gender.FEMALE));

        b.saveUsersToFile(library);


        b.readBooksFromFile(library);
        b.readUsersFromFile(library);
        LibraryManagement zb = new LibraryManagement(library);
        User user = new User("Sebastian", "Zaleski", Gender.MALE);
        library.addUser(user);

        zb.borrowBook(library.getAllBooks().get(44), user);
        zb.borrowBook(library.getAllBooks().get(57), user);
        zb.borrowBook(library.getAllBooks().get(111), user);
        zb.borrowBook(library.getAllBooks().get(178), user);
        zb.borrowBook(library.getAllBooks().get(11), library.getUsersList().get(0));
        zb.borrowBook(library.getAllBooks().get(17), library.getUsersList().get(0));
        zb.borrowBook(library.getAllBooks().get(100), library.getUsersList().get(2));
        zb.borrowBook(library.getAllBooks().get(101), library.getUsersList().get(3));

        zb.displayAllBorrowedBooks();
        b.saveUsersToFile(library);
        b.saveRegistryToFile(library);





    }

}
