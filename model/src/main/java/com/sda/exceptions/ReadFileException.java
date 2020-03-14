package com.sda.exceptions;

import java.io.IOException;

public class ReadFileException extends IOException {

    private String fileName;

    public ReadFileException(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getMessage() {
        return "Could not open file: " + fileName;
    }
}
