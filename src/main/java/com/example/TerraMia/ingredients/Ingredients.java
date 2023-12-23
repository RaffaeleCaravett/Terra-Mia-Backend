package com.example.TerraMia.ingredients;

import com.example.TerraMia.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
 @Table(name="ingredients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private double prezzo;
    @ManyToMany(mappedBy = "ingredients")
    private List<Product> products;
    public Ingredients(String nome, double prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }
}
