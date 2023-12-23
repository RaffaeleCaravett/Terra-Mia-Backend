package com.example.TerraMia.product;

import com.example.TerraMia.User.User;
import com.example.TerraMia.exceptions.BadRequestException;
import com.example.TerraMia.exceptions.NotFoundException;
import com.example.TerraMia.payloads.ProductDTO;
import com.example.TerraMia.payloads.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getProducts(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String orderBy){
        return productService.getProducts(page, size, orderBy);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable int id)  {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public User findByIdAndUpdate(@PathVariable int id, @RequestBody UserRegistrationDTO body) throws NotFoundException {
        return productService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable int id) throws NotFoundException {
        return productService.findByIdAndDelete(id);
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public User saveProduct(@RequestBody @Validated ProductDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return productService.saveProduct(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
