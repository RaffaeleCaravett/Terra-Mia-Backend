package com.example.TerraMia.order;

import com.example.TerraMia.exceptions.BadRequestException;
import com.example.TerraMia.exceptions.NotFoundException;
import com.example.TerraMia.payloads.entities.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Order> getOrders(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String orderBy){
        return orderService.getOrders(page, size, orderBy);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Order findById(@PathVariable int id)  {
        return orderService.findById(id);
    }

    @PutMapping("/{id}")
    public Order findByIdAndUpdate(@PathVariable int id, @RequestBody OrderDTO body) throws NotFoundException {
        return orderService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable int id) throws NotFoundException {
        orderService.findByIdAndDelete(id);
    }
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Order saveOrder(@RequestBody @Validated OrderDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return orderService.saveOrder(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @GetMapping("/byYear")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Order>> findOrdersByYear(@RequestParam int year) {
        List<Order> orders = orderService.findOrdersByYear(year);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/byYearAndMonth")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Order>> findOrdersByYearAndMonth(@RequestParam int year, @RequestParam Integer month) {
        List<Order> orders = orderService.findOrdersByYearAndMonth(year, month);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/byDate")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Order>> findOrdersByDate(@RequestParam int year, @RequestParam Integer month,@RequestParam Integer day) {
        List<Order> orders = orderService.findOrdersByDate(year, month, day);
        return ResponseEntity.ok(orders);
    }

}
