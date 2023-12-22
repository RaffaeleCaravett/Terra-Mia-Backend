package com.example.TerraMia.product;

import com.example.TerraMia.enums.ProductType;
import com.example.TerraMia.ingredients.Ingredients;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private ProductType productType;
    private double price;
    private List<Ingredients> ingredients;
    private String requests;

}
