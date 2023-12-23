package com.example.TerraMia.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record IngredientDTO(

        long id,

        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        String nome,
        @NotNull(message = "Il price è un campo obbligatorio!")
        Double price
) {
}
