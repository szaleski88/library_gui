package com.sda.gui;

import com.sda.controller.Backup;
import com.sda.controller.ZarzadzanieBiblioteka;
import com.sda.model.*;
import com.sda.sortowanie.SortowanieSzybkie;
import com.sda.wyszukiwanie.WyszukiwanieBinarne;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class KontrolerGUI {

    private static Biblioteka biblioteka;
    private static ZarzadzanieBiblioteka zb;
    private static Backup backup;
    private static WyszukiwanieBinarne wyszukaj;
    private static SortowanieSzybkie sortuj;

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
    private  TableView<Ksiazka> tabelaSzukaj;
    @FXML
    private TableColumn<Ksiazka, String> kolumnaTytul;
    @FXML
    private TableColumn<Ksiazka, Autor> kolumnaAutor;
    @FXML
    private TableColumn<Ksiazka, Gatunek> kolumnaGatunek;
    @FXML
    private TableColumn<Ksiazka, Integer> kolumnaRokWydania;
    @FXML
    private TableColumn<Ksiazka, Boolean> kolumnaStatus;
    @FXML
    private TableColumn<Wpis, Autor> kolumnaAutorWypozyczony;
    @FXML
    private TableColumn<Wpis, Uzytkownik> kolumnaUzytkownikWypozyczony;
    @FXML
    private TableColumn<Wpis, LocalDate> kolumnaDataWypozyczenia;
    @FXML
    private TableColumn<Wpis, String> kolumnaTytulWypozyczony;
    @FXML private  TableColumn<Uzytkownik, String> kolumnaUzytkImie;
    @FXML private TableColumn<Uzytkownik, String> kolumnaUzytkNazwisko;
    @FXML private TableColumn<Uzytkownik, Plec> kolUzytkPlec;

    @FXML
    private TableView<Wpis> tabelaWypozyczone;
    @FXML
    private TableView<Uzytkownik> tableViewUzytkownicy;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField textFieldNazwiskoWypozyczajacego;
    @FXML
    private TextField textFieldImieWypozyczajacego;
    @FXML private TextField textFieldImieUzytkSzukaj;
    @FXML private TextField textFieldNazwiskoUzytkSzukaj;


    public KontrolerGUI() {
        biblioteka = new Biblioteka();
        zb = new ZarzadzanieBiblioteka(biblioteka);
        wyszukaj = new WyszukiwanieBinarne(biblioteka);
        sortuj = new SortowanieSzybkie();
        backup = new Backup();
        try {
            backup.odczytKsiazek(biblioteka);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            backup.odczytUzytkownikow(biblioteka);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            backup.odczytRejestru(biblioteka);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
//        zb.wyswietlListeKsiazek();
//        dropDown.getItems().addAll(Plec.values());
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


    private void wyswietlWyniki(List<Ksiazka> listaKsiazek){

        textFieldNazwiskoAutora.setText("");
        textFieldImieAutora.setText("");
        textFieldTytul.setText("");

        ObservableList<Ksiazka> data = FXCollections.observableArrayList(listaKsiazek);

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
        Uzytkownik uzytkownik = new Uzytkownik(textFieldImieUzytkownika.getText().trim(),
                                                textFieldNazwiskoUzytkownika.getText().trim(),
                                                Plec.valueOf(comboBox.getValue().toUpperCase()));
        biblioteka.dodajUzytkownika(uzytkownik);

        textFieldImieUzytkownika.setText("");
        textFieldNazwiskoUzytkownika.setText("");
        System.out.println(uzytkownik);
    }


    @FXML
    public void zwrocZaznaczoneKsiazki() {
        List<Wpis> wpisy = tabelaWypozyczone.getSelectionModel().getSelectedItems();
        for (Wpis wpis : wpisy) {
            wpis.setDataZwrotu(LocalDate.now());
            zmienStatusKsiazki(wpis.getKsiazka());

            System.out.println("Pomyślnie zwrócono ksiązkę: " + wpis.getKsiazka().getTytul());
        }
    }

    private void zmienStatusKsiazki(Ksiazka ksiazka) {
        List<Ksiazka> ksiazkaSzukana = biblioteka.getListaKsiazek().stream().filter(ks -> ks.getID().equals(ksiazka.getID())).collect(Collectors.toList());
        ksiazkaSzukana.get(0).setDostepna(true);
    }

    @FXML
    public void szukajWypozyczonychUzytkownika() {
        List<Wpis> wypUzytk = zb.getWypozyczonePrzezUzytkownika(textFieldImieWypozyczajacego.getText(),
                textFieldNazwiskoWypozyczajacego.getText());
        if( wypUzytk!= null) wypelnijWypozyczone(wypUzytk);
    }

    @FXML
    public void szukajWypozyczonych() {
        wypelnijWypozyczone(zb.getWypozyczoneKsiazki());

    }

    private void wypelnijWypozyczone(List<Wpis> wpisy) {

        ObservableList<Wpis> data = FXCollections.observableArrayList(wpisy);

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
        backup.zapisRejestru(biblioteka);
        backup.zapisUzytkownikow(biblioteka);
        backup.zapisKsiazek(biblioteka);
        Platform.exit();
    }

    public void wypozyczKsiazke() {
        Uzytkownik uz = tableViewUzytkownicy.getSelectionModel().getSelectedItem();
        Ksiazka ks = tabelaSzukaj.getSelectionModel().getSelectedItem();
        System.out.println();

        zb.wypozyczKsiazke(ks, uz);
        System.out.println("Wypozyczone!!!");

    }

    public void szukajUzytkownika() {
        String imie = textFieldImieUzytkSzukaj.getText();
        String nazwisko = textFieldNazwiskoUzytkSzukaj.getText();

        List<Uzytkownik> uzytkownikSzukany = wyszukaj.szukajUzytkownika(imie, nazwisko);
        System.out.println("Znaleziono uzytkownika: " + uzytkownikSzukany.size());

        wypelnijUzytkownikow(uzytkownikSzukany);
    }

    public void szukajWszystkichUzytkownikow() {
        List<Uzytkownik> uzytkownicy = biblioteka.getListaUzytkownikow();

        wypelnijUzytkownikow(uzytkownicy);
    }

    private void wypelnijUzytkownikow(List<Uzytkownik> uzytkownicy) {

        ObservableList<Uzytkownik> data = FXCollections.observableArrayList(uzytkownicy);

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dodajKsiazke.fxml"));
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
