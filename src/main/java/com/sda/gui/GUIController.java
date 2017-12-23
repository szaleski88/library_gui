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
    private static LibraryManagement zb;
    private static Backup backup;
    private static BinarySearch wyszukaj;
    private static QuickSort sortuj;

    @FXML
    private   TextField textFieldTytul;
    @FXML
    private   TextField textFieldImieAutora;
    @FXML
    private   TextField textFieldImieUzytkownika;
    @FXML
    private   TextField textFieldNazwiskoAutora;
    @FXML
    private   TextField textFieldNazwiskoUzytkownika;
    @FXML
    private   Button buttonDodajUzytkownika;
    @FXML
    private Button buttonSzukajAutora;
    @FXML
    private Button buttonSzukajTytulu;
    @FXML
    private  TableView<Book> tabelaSzukaj;
    @FXML
    private TableColumn<Book, String> kolumnaTytul;
    @FXML
    private TableColumn<Book, Author> kolumnaAutor;
    @FXML
    private TableColumn<Book, Genre> kolumnaGatunek;
    @FXML
    private TableColumn<Book, Integer> kolumnaRokWydania;
    @FXML
    private TableColumn<Book, Boolean> kolumnaStatus;
    @FXML
    private TableColumn<LogEntry, Author> kolumnaAutorWypozyczony;
    @FXML
    private TableColumn<LogEntry, User> kolumnaUzytkownikWypozyczony;
    @FXML
    private TableColumn<LogEntry, LocalDate> kolumnaDataWypozyczenia;
    @FXML
    private TableColumn<LogEntry, String> kolumnaTytulWypozyczony;
    @FXML private  TableColumn<User, String> kolumnaUzytkImie;
    @FXML private TableColumn<User, String> kolumnaUzytkNazwisko;
    @FXML private TableColumn<User, Gender> kolUzytkPlec;

    @FXML
    private TableView<LogEntry> tabelaWypozyczone;
    @FXML
    private TableView<User> tableViewUzytkownicy;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField textFieldNazwiskoWypozyczajacego;
    @FXML
    private TextField textFieldImieWypozyczajacego;
    @FXML private TextField textFieldImieUzytkSzukaj;
    @FXML private TextField textFieldNazwiskoUzytkSzukaj;


    public GUIController() {
        library = new Library();
        zb = new LibraryManagement(library);
        wyszukaj = new BinarySearch(library);
        sortuj = new QuickSort();
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
            backup.readLogsFromFile(library);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
//        zb.wyswietlListeKsiazek();
//        dropDown.getItems().addAll(Gender.values());
    }

   @FXML
   private void szukajTytulu(){

       String tytul = textFieldTytul.getText();
       wyswietlWyniki(zb.wyszukajPoTytule(tytul));
   }

    @FXML
    private void szukajAutora(){
        String imie = textFieldImieAutora.getText();
        String nazwisko = textFieldNazwiskoAutora.getText();
        wyswietlWyniki(zb.wyszukajPoAutorze(imie, nazwisko));
    }


    private void wyswietlWyniki(List<Book> listaKsiazek){

        textFieldNazwiskoAutora.setText("");
        textFieldImieAutora.setText("");
        textFieldTytul.setText("");

        ObservableList<Book> data = FXCollections.observableArrayList(listaKsiazek);

        kolumnaAutor.setCellValueFactory(
                new PropertyValueFactory<>("autor")
        );
        kolumnaTytul.setCellValueFactory(
                new PropertyValueFactory<>("tytul")
        );
        kolumnaRokWydania.setCellValueFactory(
                new PropertyValueFactory("rokWydania")
        );
        kolumnaGatunek.setCellValueFactory(
                new PropertyValueFactory("gatunek")
        );
        kolumnaStatus.setCellValueFactory(
                new PropertyValueFactory("dostepna")
        );

        tabelaSzukaj.setItems(data);
        tabelaSzukaj.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );

    }

    @FXML
    private void dodajUzytkownika(){
        User user = new User(textFieldImieUzytkownika.getText().trim(),
                                                textFieldNazwiskoUzytkownika.getText().trim(),
                                                Gender.valueOf(comboBox.getValue().toUpperCase()));
        library.addUser(user);

        textFieldImieUzytkownika.setText("");
        textFieldNazwiskoUzytkownika.setText("");
        System.out.println(user);
    }


    @FXML
    public void zwrocZaznaczoneKsiazki() {
        List<LogEntry> wpisy = tabelaWypozyczone.getSelectionModel().getSelectedItems();
        for (LogEntry logEntry : wpisy) {
            logEntry.setDataZwrotu(LocalDate.now());
            zmienStatusKsiazki(logEntry.getBook());

            System.out.println("Pomyślnie zwrócono ksiązkę: " + logEntry.getBook().getTytul());
        }
    }

    private void zmienStatusKsiazki(Book book) {
        List<Book> bookSzukana = library.getListaKsiazek().stream()
                                        .filter(ks -> ks.getID().equals(book.getID())).collect(Collectors.toList());
        bookSzukana.get(0).setDostepna(true);
    }

    @FXML
    public void szukajWypozyczonychUzytkownika() {
        List<LogEntry> wypUzytk = zb.getWypozyczonePrzezUzytkownika(textFieldImieWypozyczajacego.getText(),
                textFieldNazwiskoWypozyczajacego.getText());
        if( wypUzytk!= null) wypelnijWypozyczone(wypUzytk);
    }

    @FXML
    public void szukajWypozyczonych() {
        wypelnijWypozyczone(zb.getWypozyczoneKsiazki());

    }

    private void wypelnijWypozyczone(List<LogEntry> wpisy) {

        ObservableList<LogEntry> data = FXCollections.observableArrayList(wpisy);

        kolumnaAutorWypozyczony.setCellValueFactory(
                new PropertyValueFactory<>("autor")
        );
        kolumnaTytulWypozyczony.setCellValueFactory(
                new PropertyValueFactory<>("tytul")
        );
        kolumnaDataWypozyczenia.setCellValueFactory(
                new PropertyValueFactory("dataWypozyczenia")
        );
        kolumnaUzytkownikWypozyczony.setCellValueFactory(
                new PropertyValueFactory("uzytkownik")
        );

        tabelaWypozyczone.setItems(data);
        tabelaWypozyczone.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
    }

    public void shutdown() {
        backup.saveLogsToFile(library);
        backup.saveUsersToFile(library);
        backup.saveBooksToFile(library);
        Platform.exit();
    }

    public void wypozyczKsiazke() {
        User uz = tableViewUzytkownicy.getSelectionModel().getSelectedItem();
        Book ks = tabelaSzukaj.getSelectionModel().getSelectedItem();
        System.out.println();

        zb.wypozyczKsiazke(ks, uz);
        System.out.println("Wypozyczone!!!");

    }

    public void szukajUzytkownika() {
        String imie = textFieldImieUzytkSzukaj.getText();
        String nazwisko = textFieldNazwiskoUzytkSzukaj.getText();

        List<User> userSzukany = wyszukaj.szukajUzytkownika(imie, nazwisko);
        System.out.println("Znaleziono uzytkownika: " + userSzukany.size());

        wypelnijUzytkownikow(userSzukany);
    }

    public void szukajWszystkichUzytkownikow() {
        List<User> uzytkownicy = library.getListaUzytkownikow();

        wypelnijUzytkownikow(uzytkownicy);
    }

    private void wypelnijUzytkownikow(List<User> uzytkownicy) {

        ObservableList<User> data = FXCollections.observableArrayList(uzytkownicy);

        kolumnaUzytkImie.setCellValueFactory(
                new PropertyValueFactory<>("imie")
        );
        kolumnaUzytkNazwisko.setCellValueFactory(
                new PropertyValueFactory<>("nazwisko")
        );
        kolUzytkPlec.setCellValueFactory(
                new PropertyValueFactory("plec")
        );

        tableViewUzytkownicy.setItems(data);
        tableViewUzytkownicy.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );
    }

    public void otworzOknoDodawaniaOsoby(ActionEvent actionEvent) {
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
