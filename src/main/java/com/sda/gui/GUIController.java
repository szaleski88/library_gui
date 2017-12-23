package com.sda.gui;

import com.sda.controller.Backup;
import com.sda.controller.LibraryManagement;
import com.sda.model.*;
import com.sda.sort.QuickSort;
import com.sda.search.BinarySearch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class GUIController {

    private static Library library;
    private static LibraryManagement lm;
    private static Backup backup;
    private static BinarySearch search;
    private static QuickSort quickSort;

    @FXML
    private   TextField tfTitle;
    @FXML
    private   TextField tfAuthorFirstName;
    @FXML
    private   TextField tfUserFirstName;
    @FXML
    private   TextField tfAuthorLastName;
    @FXML
    private   TextField tfUserLastName;
    @FXML
    private   Button btnAddUser;
    @FXML
    private Button btnSearchForAuthor;
    @FXML
    private Button btnSearchForTitle;
    @FXML
    private  TableView<Book> tblSearch;
    @FXML
    private TableColumn<Book, String> colTitle;
    @FXML
    private TableColumn<Book, Author> colAuthor;
    @FXML
    private TableColumn<Book, Genre> colGenre;
    @FXML
    private TableColumn<Book, Integer> colReleaseDate;
    @FXML
    private TableColumn<Book, Boolean> colStatus;
    @FXML
    private TableColumn<RegEntry, Author> colBorrowedAuthor;
    @FXML
    private TableColumn<RegEntry, User> colBorrowedUser;
    @FXML
    private TableColumn<RegEntry, LocalDate> colBorrowDate;
    @FXML
    private TableColumn<RegEntry, String> colBorrowedTitle;
    @FXML private  TableColumn<User, String> colUserFirstName;
    @FXML private TableColumn<User, String> colUserLastName;
    @FXML private TableColumn<User, Gender> colUserGender;

    @FXML
    private TableView<RegEntry> tblBorrowed;
    @FXML
    private TableView<User> tblUsers;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField tfLastNameBorrower;
    @FXML
    private TextField tfFirstNameBorrower;
    @FXML private TextField tfSearchUserFirstName;
    @FXML private TextField tfSearchUserLastName;


    public GUIController() {
        library = new Library();
        lm = new LibraryManagement(library);
        search = new BinarySearch(library);
        quickSort = new QuickSort();
        backup = new Backup();
        try {
            backup.readBooksFromFile(library);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            backup.readUsersFromFile(library);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            backup.readRegistryFromFile(library);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
//        lm.displayBooksList();
//        dropDown.getItems().addAll(Gender.values());
    }

   @FXML
   private void searchForTitle(){

       String title = tfTitle.getText();
       displayResult(lm.searchByTitle(title));
   }

    @FXML
    private void searchForAuthor(){
        String firstName = tfAuthorFirstName.getText();
        String lastName = tfAuthorLastName.getText();
        displayResult(lm.searchByAuthor(firstName, lastName));
    }


    private void displayResult(List<Book> books){

        tfAuthorLastName.setText("");
        tfAuthorFirstName.setText("");
        tfTitle.setText("");

        ObservableList<Book> data = FXCollections.observableArrayList(books);

        colAuthor.setCellValueFactory(
                new PropertyValueFactory<>("author")
        );
        colTitle.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );
        colReleaseDate.setCellValueFactory(
                new PropertyValueFactory("releaseDate")
        );
        colGenre.setCellValueFactory(
                new PropertyValueFactory("genre")
        );
        colStatus.setCellValueFactory(
                new PropertyValueFactory("available")
        );

        tblSearch.setItems(data);
        tblSearch.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );

    }

    @FXML
    private void addUser(){
        User user = new User(tfUserFirstName.getText().trim(),
                                                tfUserLastName.getText().trim(),
                                                Gender.valueOf(comboBox.getValue().toUpperCase()));
        library.addUser(user);

        tfUserFirstName.setText("");
        tfUserLastName.setText("");
        System.out.println(user);
    }


    @FXML
    public void returnBorrowedBooks() {
        List<RegEntry> entries = tblBorrowed.getSelectionModel().getSelectedItems();
        for (RegEntry regEntry : entries) {
            regEntry.setReturnDate(LocalDate.now());
            changeBookStatus(regEntry.getBook());

            System.out.println("Successfully returned book: " + regEntry.getBook().getTitle());
        }
    }

    private void changeBookStatus(Book book) {
        List<Book> searchedBook = library.getBooksList().stream()
                                        .filter(b -> b.getID().equals(b.getID())).collect(Collectors.toList());
        searchedBook.get(0).setAvailable(true);
    }

    @FXML
    public void searchForBorrowedByUser() {
        List<RegEntry> borrowedByUser = lm.getBorrowedByUser(tfFirstNameBorrower.getText(),
                tfLastNameBorrower.getText());
        if( borrowedByUser!= null) fillInBorrowed(borrowedByUser);
    }

    @FXML
    public void searchForBorrowed() {
        fillInBorrowed(lm.getBorrowedBooks());

    }

    private void fillInBorrowed(List<RegEntry> registry) {

        ObservableList<RegEntry> data = FXCollections.observableArrayList(registry);

        colBorrowedAuthor.setCellValueFactory(
                new PropertyValueFactory<>("author")
        );
        colBorrowedTitle.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );
        colBorrowDate.setCellValueFactory(
                new PropertyValueFactory("releaseDate")
        );
        colBorrowedUser.setCellValueFactory(
                new PropertyValueFactory("user")
        );

        tblBorrowed.setItems(data);
        tblBorrowed.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
    }

    public void shutdown() {
        backup.saveRegistryToFile(library);
        backup.saveUsersToFile(library);
        backup.saveBooksToFile(library);
        Platform.exit();
    }

    public void borrowBook() {
        User us = tblUsers.getSelectionModel().getSelectedItem();
        Book bk = tblSearch.getSelectionModel().getSelectedItem();
        System.out.println();

        lm.borrowBook(bk, us);
        System.out.println("Borrowed!!!");

    }

    public void findUser() {
        String firstName = tfSearchUserFirstName.getText();
        String lastName = tfSearchUserLastName.getText();

        List<User> searchedUser = search.searchForUser(firstName, lastName);
        System.out.println("Found User: " + searchedUser.size());

        fillInUsers(searchedUser);
    }

    public void findAllUsers() {
        List<User> users = library.getUsersList();

        fillInUsers(users);
    }

    private void fillInUsers(List<User> users) {

        ObservableList<User> data = FXCollections.observableArrayList(users);

        colUserFirstName.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        colUserLastName.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        colUserGender.setCellValueFactory(
                new PropertyValueFactory("gender")
        );

        tblUsers.setItems(data);
        tblUsers.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );
    }

    public void openAddUserWindow(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addBook.fxml"));
//            root = FXMLLoader.load(getClass().getResource("BibliotekaGUI.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            // to sprawia że okno spod spodu jest ZABLOKOWANE!!!!
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
