package com.example.TerraMia.order;

import com.example.TerraMia.User.User;
import com.example.TerraMia.enums.OrderState;
import com.example.TerraMia.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int coperti;
    @ManyToMany
    @JoinTable(name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> prodotti;
    private double totale;
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User createdBy;
    private OrderState state;

    public Order(int coperti, List<Product> prodotti, User createdBy, OrderState state) {
        this.coperti = coperti;
        this.prodotti = prodotti;
        this.createdBy = createdBy;
        this.state = state;
    }
}
