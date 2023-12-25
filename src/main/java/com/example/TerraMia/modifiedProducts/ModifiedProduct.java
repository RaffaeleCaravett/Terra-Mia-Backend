package com.example.TerraMia.modifiedProducts;

import com.example.TerraMia.enums.ProductType;
import com.example.TerraMia.ingredients.Ingredients;
import com.example.TerraMia.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="modified_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModifiedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private double price;
    @ManyToMany
    @JoinTable(name = "modifiedProduct_ingredients",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredients> ingredients;
    private String requests;
    @JsonIgnore
    @ManyToMany(mappedBy = "prodotti")
    private List<Order> orders;

    public ModifiedProduct(String nome, ProductType productType, double price, List<Ingredients> ingredients, String requests) {
        this.nome = nome;
        this.productType = productType;
        this.price = price;
        this.ingredients = ingredients;
        this.requests = requests;
    }
}

