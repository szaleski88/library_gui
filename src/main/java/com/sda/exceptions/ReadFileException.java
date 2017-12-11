package com.sda.exceptions;

import java.io.IOException;

public class ReadFileException extends IOException{

    private String nazwaPliku;

    public ReadFileException(String nazwaPliku){
        this.nazwaPliku = nazwaPliku;
    }


    @Override
    public String getMessage() {
        return "Nie udalo sie wczytac listy: " + nazwaPliku ;
    }
}
