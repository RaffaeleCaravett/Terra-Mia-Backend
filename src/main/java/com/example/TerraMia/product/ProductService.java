package com.example.TerraMia.product;

import com.example.TerraMia.User.User;
import com.example.TerraMia.enums.ProductType;
import com.example.TerraMia.exceptions.BadRequestException;
import com.example.TerraMia.exceptions.NotFoundException;
import com.example.TerraMia.ingredients.Ingredients;
import com.example.TerraMia.ingredients.IngredientsRepository;
import com.example.TerraMia.payloads.ProductDTO;
import com.example.TerraMia.payloads.UserRegistrationDTO;
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
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    IngredientsRepository ingredientsRepository;
      public Product saveProduct(ProductDTO body) throws IOException {

          List<Ingredients> ingredientsList= new ArrayList<>();

          for(Long l: body.ingredients()){
              ingredientsList.add(ingredientsRepository.findById(l).get());
          }
          ProductType p = ProductType.valueOf(body.productType());


        Product product = new Product(body.nome(), p, body.price(),ingredientsList, body.requests());
        return productRepository.save(product);
    }

    public Page<Product> getProducts(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return productRepository.findAll(pageable);
    }

    public Product findById(long id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Product findByIdAndUpdate(long id, ProductDTO body) throws NotFoundException {
        Product found = productRepository.findById(id).get();

        //found.setPassword(bcrypt.encode(body.getPassword()));
        return productRepository.save(found);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Product found = this.findById(id);
        productRepository.delete(found);
    }

    public List<Product> findByProductType(ProductType productType) throws NotFoundException {
         return productRepository.findByProductType(productType);
    }

}
