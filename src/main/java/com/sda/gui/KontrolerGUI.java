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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.List;


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
    @FXML
    private TableView<Wpis> tabelaWypozyczone;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField textFieldNazwiskoWypozyczajacego;
    @FXML
    private TextField textFieldImieWypozyczajacego;



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
        zb.wyswietlWypozyczoneKsiazki();
//        zb.wyswietlListeKsiazek();
//        dropDown.getItems().addAll(Plec.values());
        // tableSetup();
    }

   @FXML
   private void szukajTytulu(){

       String tytul = textFieldTytul.getText();
       System.out.println(tytul);
       List<Ksiazka> listaKsiazke = wyszukaj.szukajTytul(tytul);
       System.out.println(listaKsiazke.size());
       //for (Ksiazka ksiazka :  listaKsiazke) {
       //    System.out.println(ksiazka);
       //}
       wyswietlWyniki(listaKsiazke);
   }

    @FXML
    private void szukajAutora(){
        String imie = textFieldImieAutora.getText();
        String nazwisko = textFieldNazwiskoAutora.getText();
        System.out.println(imie + " " + nazwisko);
        List<Ksiazka> listaKsiazke = wyszukaj.szukajAutora(imie, nazwisko);
        System.out.println("Znaleziomo: " + listaKsiazke.size());
        wyswietlWyniki(listaKsiazke);
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
                new PropertyValueFactory<Ksiazka, Integer>("rokWydania")
        );
        kolumnaGatunek.setCellValueFactory(
                new PropertyValueFactory<Ksiazka, Gatunek>("gatunek")
        );
        kolumnaStatus.setCellValueFactory(
                new PropertyValueFactory<Ksiazka, Boolean>("dostepna")
        );

        tabelaSzukaj.setItems(data);

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


    private void zwrocKsiazki(){
        ObservableList<Ksiazka> ksiazka, ksiazki;
        ksiazki = tabelaSzukaj.getItems();
        ksiazka = tabelaSzukaj.getSelectionModel().getSelectedItems();

//        ksiazka.forEach(coszrob);

     }

    @FXML
    public void zwrocZaznaczoneKsiazki() {
    }

    @FXML
    public void szukajWypozyczonychUzytkownika() {
        List<Wpis> wypUzytk = zb.getWypozyczonePrzezUzytkownika(textFieldImieWypozyczajacego.getText(),
                textFieldNazwiskoWypozyczajacego.getText());
        wypelnijWypozyczone(wypUzytk);
    }

    @FXML
    public void szukajWypozyczonych() {
        wypelnijWypozyczone(zb.getWypozyczoneKsiazki());

    }

    private void wypelnijWypozyczone(List<Wpis> wpisy) {
        textFieldNazwiskoAutora.setText("");
        textFieldImieAutora.setText("");
        textFieldTytul.setText("");

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


    }

    public void shutdown() {
        backup.zapisRejestru(biblioteka);
        backup.zapisUzytkownikow(biblioteka);
        backup.zapisKsiazek(biblioteka);
        Platform.exit();
    }
}
