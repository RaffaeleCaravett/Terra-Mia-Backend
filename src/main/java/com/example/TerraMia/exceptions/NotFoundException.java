package com.example.TerraMia.exceptions;

import java.time.LocalDate;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id) {
        super("L'elemento con id: " + id + " non è stato trovato. Riprovare con un id diverso");
    }
    public NotFoundException(String elem) {
        super(elem);
    }


}

