package com.example.TerraMia.ingredients;

import com.example.TerraMia.exceptions.BadRequestException;
import com.example.TerraMia.exceptions.NotFoundException;
import com.example.TerraMia.payloads.entities.IngredientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    @Autowired
    IngredientsService ingredientsService;
    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Ingredients> getIngredients(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String orderBy){
        return ingredientsService.getIngredients(page, size, orderBy);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Ingredients findById(@PathVariable int id)  {
        return ingredientsService.findById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Ingredients saveIngredient(@RequestBody @Validated IngredientDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return ingredientsService.saveIngredient(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @PutMapping("/{id}")
    public Ingredients findByIdAndUpdate(@PathVariable int id, @RequestBody IngredientDTO body) throws NotFoundException {
        return ingredientsService.findByIdAndUpdate(id, body);
    }
}
