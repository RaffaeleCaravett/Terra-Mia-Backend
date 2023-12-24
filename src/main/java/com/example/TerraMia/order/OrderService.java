package com.example.TerraMia.order;

import com.example.TerraMia.User.User;
import com.example.TerraMia.User.UserRepository;
import com.example.TerraMia.enums.OrderState;
import com.example.TerraMia.exceptions.NotFoundException;
import com.example.TerraMia.payloads.entities.OrderDTO;
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
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    public Order saveOrder(OrderDTO body) throws IOException {

        List<Product> productList = new ArrayList<>();

        for (Long l : body.products()) {
            productList.add(productRepository.findById(l).get());
        }

        OrderState s = OrderState.valueOf(body.state());

        User user = userRepository.findById(body.user()).get();
        Order product = new Order(body.coperti(), productList, user, s);
        return orderRepository.save(product);
    }

    public Page<Order> getOrders(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return orderRepository.findAll(pageable);
    }

    public Order findById(long id) throws NotFoundException {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Order findByIdAndUpdate(long id, OrderDTO body) throws NotFoundException {
        Order found = orderRepository.findById(id).get();

        OrderState s = OrderState.valueOf(body.state());

        List<Product> productList = new ArrayList<>();

        for (Long l : body.products()) {
            productList.add(productRepository.findById(l).get());
        }
        User user = userRepository.findById(body.user()).get();

        found.setCoperti(body.coperti());
        found.setState(s);
        found.setProdotti(productList);
        found.setCreatedBy(user);

        return orderRepository.save(found);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Order found = this.findById(id);
        orderRepository.delete(found);
    }

    public List<Order> findOrdersByYear(int year) {
        return orderRepository.findOrdersByYear(year);
    }

    public List<Order> findOrdersByYearAndMonth(int year, int month) {
        return orderRepository.findOrdersByYearAndMonth(year, month);
    }

    public List<Order> findOrdersByDate(int year, int month, int day) {
        return orderRepository.findOrdersByDate(year, month, day);
    }

}
