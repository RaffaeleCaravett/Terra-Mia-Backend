package com.example.TerraMia.order;

import com.example.TerraMia.User.User;
import com.example.TerraMia.enums.OrderState;
import com.example.TerraMia.modifiedProducts.ModifiedProduct;
import com.example.TerraMia.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @JoinTable(name = "order_modifiedProducts",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ModifiedProduct> prodotti;
    private double totale;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User createdBy;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    private String tavolo;
    public Order(int coperti, List<ModifiedProduct> prodotti, User createdBy, OrderState state,String tavolo) {
        this.coperti = coperti;
        this.prodotti = prodotti;
        this.createdBy = createdBy;
        this.state = state;
        this.tavolo=tavolo;
        this.createdAt= LocalDateTime.now();
        for(ModifiedProduct p: prodotti){
            this.totale+=p.getPrice();
        }
        for(int i =1;i<=this.coperti;i++){
            this.totale+=1;
        }
    }

}
