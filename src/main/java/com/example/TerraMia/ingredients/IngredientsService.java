package com.example.TerraMia.ingredients;

import com.example.TerraMia.enums.ProductType;
import com.example.TerraMia.exceptions.NotFoundException;
import com.example.TerraMia.payloads.IngredientDTO;
import com.example.TerraMia.payloads.ProductDTO;
import com.example.TerraMia.product.Product;
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
public class IngredientsService {

    @Autowired
    IngredientsRepository ingredientsRepository;

    public Ingredients saveIngredient(IngredientDTO body) throws IOException {

        Ingredients ingredient = new Ingredients(body.nome(),body.price());
        return ingredientsRepository.save(ingredient);
    }

    public Page<Ingredients> getIngredients(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return ingredientsRepository.findAll(pageable);
    }

    public Ingredients findById(long id) throws NotFoundException {
        return ingredientsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Ingredients findByIdAndUpdate(long id, IngredientDTO body) throws NotFoundException {
        Ingredients found = ingredientsRepository.findById(id).get();

        //found.setPassword(bcrypt.encode(body.getPassword()));
        return ingredientsRepository.save(found);
    }
}
