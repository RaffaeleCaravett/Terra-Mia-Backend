package com.example.TerraMia.order;

import com.example.TerraMia.User.User;
import com.example.TerraMia.enums.OrderState;
import com.example.TerraMia.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int coperti;
    private List<Product> prodotti;
    private double totale;
    private LocalDate createdAt;
    private User createdBy;
    private OrderState state;

}
