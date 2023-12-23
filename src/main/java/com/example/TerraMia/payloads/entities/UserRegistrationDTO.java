package com.example.TerraMia.payloads.entities;

import jakarta.validation.constraints.NotEmpty;

public record UserRegistrationDTO(
        long id,

        @NotEmpty(message = "L'email è un campo obbligatorio!")
        String email,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        String password,
        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        String nome,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        String cognome
){}
