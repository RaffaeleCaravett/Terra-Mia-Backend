package com.example.TerraMia.ingredients;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
 @Table(name="ingredients")
@Getter
@Setter
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private double prezzo;

    public Ingredients(String nome, double prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }
}
