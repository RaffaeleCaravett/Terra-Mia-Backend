package com.example.TerraMia.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderDTO(

        long id,

        @NotNull(message="coperti obbligatori")
        int coperti,

        List<Long> products,
        @NotNull(message="user obbligatorio")
        Long user,

        @NotEmpty(message="Stato obbligatorio")
String state
) {
}
