package com.sda.gui;

import com.sda.model.Biblioteka;

public class UI {
    public static void main(String[] args) {
       Biblioteka biblioteka = new Biblioteka();


        System.out.println(biblioteka.getListaKsiazek());
    }
}
