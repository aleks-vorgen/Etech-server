package com.etech.controller;

import com.etech.model.Order;
import com.etech.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("all")
    public List<Order> getOrderList() {
        return orderRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(name = "id") Long id) {
        Order result = orderRepository.findById(id).orElse(null);

        if (result == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
