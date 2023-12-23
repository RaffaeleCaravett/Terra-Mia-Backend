package com.example.TerraMia.ingredients;

import com.example.TerraMia.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients,Long> {
}
