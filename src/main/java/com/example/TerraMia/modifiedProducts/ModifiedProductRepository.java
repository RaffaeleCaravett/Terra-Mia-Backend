package com.example.TerraMia.modifiedProducts;

import com.example.TerraMia.enums.ProductType;
import com.example.TerraMia.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModifiedProductRepository extends JpaRepository<ModifiedProduct,Long> {
    List<ModifiedProduct> findByProductType(ProductType productType);
}
