package com.example.TerraMia.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProductDTO(

        long id,

        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        String nome,
        @NotEmpty(message = "Il tipo è un campo obbligatorio!")
        String productType,
        @NotNull(message = "Il price è un campo obbligatorio!")
        Double price,
        @NotNull(message = "Gli ingredients sono un campo obbligatorio!")
        List<Long> ingredients,
        String requests
) {

}
