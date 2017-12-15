package com.sda.gui;

import com.sda.controller.Backup;
import com.sda.controller.ZarzadzanieBiblioteka;
import com.sda.model.Autor;
import com.sda.model.Biblioteka;
import com.sda.model.Gatunek;
import com.sda.model.Ksiazka;
import com.sda.sortowanie.SortowanieSzybkie;
import com.sda.wyszukiwanie.WyszukiwanieBinarne;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.bind.JAXBException;
import javax.xml.soap.Text;
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
        zb.wyswietlListeKsiazek();
        // tableSetup();
    }

    private void tableSetup(){
        kolumnaAutor.setCellValueFactory(
                new PropertyValueFactory<Ksiazka, Autor>("autor")
        );
        kolumnaTytul.setCellValueFactory(
                new PropertyValueFactory<Ksiazka, String>("tytul")
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
        List<Ksiazka> listaKsiazke = wyszukaj.szukajAutora(imie, nazwisko);
        System.out.println("Znaleziomo: " + listaKsiazke.size());
        wyswietlWyniki(listaKsiazke);
    }


    private void wyswietlWyniki(List<Ksiazka> listaKsiazek){
        textFieldNazwiskoAutora.clear();
        textFieldImieAutora.clear();
        textFieldTytul.clear();
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
//        for (Ksiazka ksiazka : listaKsiazek) {
//            Ksiazka ksiazka =
//            tabelaSzukaj.getItems().add(ksiazka);
//        }
    }


    private void zwrocKsiazki(){
        ObservableList<Ksiazka> ksiazka, ksiazki;
        ksiazki = tabelaSzukaj.getItems();
        ksiazka = tabelaSzukaj.getSelectionModel().getSelectedItems();

//        ksiazka.forEach(coszrob);

     }
}
