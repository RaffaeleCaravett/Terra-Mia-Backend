package com.example.TerraMia.modifiedProducts;

import com.example.TerraMia.enums.ProductType;
import com.example.TerraMia.exceptions.NotFoundException;
import com.example.TerraMia.ingredients.Ingredients;
import com.example.TerraMia.ingredients.IngredientsRepository;
import com.example.TerraMia.payloads.entities.ProductDTO;
import com.example.TerraMia.product.Product;
import com.example.TerraMia.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ModifiedProductService {

    @Autowired
    ModifiedProductRepository productRepository;

    @Autowired
    IngredientsRepository ingredientsRepository;
    public ModifiedProduct saveProduct(ProductDTO body) throws IOException {

        List<Ingredients> ingredientsList= new ArrayList<>();

        for(Long l: body.ingredients()){
            ingredientsList.add(ingredientsRepository.findById(l).get());
        }
        ProductType p = ProductType.valueOf(body.productType());


        ModifiedProduct product = new ModifiedProduct(body.nome(), p, body.price(),ingredientsList, body.requests());
        return productRepository.save(product);
    }

    public Page<ModifiedProduct> getProducts(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return productRepository.findAll(pageable);
    }

    public ModifiedProduct findById(long id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public ModifiedProduct findByIdAndUpdate(long id, ProductDTO body) throws NotFoundException {
        ModifiedProduct found = productRepository.findById(id).get();
        List<Ingredients> ingredientsList= new ArrayList<>();

        for(Long l: body.ingredients()){
            ingredientsList.add(ingredientsRepository.findById(l).get());
        }
        ProductType p = ProductType.valueOf(body.productType());
        found.setNome(body.nome());
        found.setIngredients(ingredientsList);
        found.setPrice(body.price());
        found.setProductType(p);
        found.setRequests(body.requests());
        return productRepository.save(found);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        ModifiedProduct found = this.findById(id);
        productRepository.delete(found);
    }

    public List<ModifiedProduct> findByProductType(ProductType productType) throws NotFoundException {
        return productRepository.findByProductType(productType);
    }

}
