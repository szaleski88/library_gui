package com.sda.gui;

import com.sda.controller.Backup;
import com.sda.controller.ZarzadzanieBiblioteka;
import com.sda.model.Biblioteka;
import com.sda.model.Ksiazka;
import com.sda.sortowanie.SortowanieSzybkie;
import com.sda.wyszukiwanie.WyszukiwanieBinarne;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private  TableView tabelaSzukaj;

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
    }

   @FXML
   private void szukajTytulu(){
       System.out.println("HERE");
       String tytul = textFieldTytul.getText();
       System.out.println(tytul);
       List<Ksiazka> listaKsiazke = wyszukaj.szukajTytul(tytul);
       System.out.println(listaKsiazke.size());
       for (Ksiazka ksiazka :  listaKsiazke) {
           System.out.println(ksiazka);
       }
   }

    @FXML
    private void szukajAutora(){
        System.out.println("HERE");
        String imie = textFieldImieAutora.getText();
        String nazwisko = textFieldNazwiskoAutora.getText();
        List<Ksiazka> listaKsiazke = wyszukaj.szukajAutora(imie, nazwisko);
        wyswietlWyniki(listaKsiazke);
    }


    private void wyswietlWyniki(List<Ksiazka> listaKsiazek){
        textFieldNazwiskoAutora.clear();
        textFieldImieAutora.clear();
        textFieldTytul.clear();



        for (Ksiazka ksiazka : listaKsiazek) {
//            Ksiazka ksiazka =
            tabelaSzukaj.getItems().add(ksiazka);
        }
    }


    private void zwrocKsiazki(){
        ObservableList<Ksiazka> ksiazka, ksiazki;
        ksiazki = tabelaSzukaj.getItems();
        ksiazka = tabelaSzukaj.getSelectionModel().getSelectedItems();

//        ksiazka.forEach(coszrob);

     }
}
