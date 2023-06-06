package com.etech.controller;

import com.etech.model.Order;
import com.etech.model.OrderStatus;
import com.etech.repository.OrderRepository;
import com.etech.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    private static final Long ORDER_STATUS_ACCEPTED = 1L;

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

    @PostMapping("/order")
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        if (order.getFirstname() == null || order.getLastname() == null
                || order.getMiddlename() == null || order.getPhone() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Недостатньо даних для оформлення замовлення");

        if (order.getProductList() == null || order.getProductList().size() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Відсутній список товарів до замовлення");
        }

        OrderStatus status = orderStatusRepository.findById(ORDER_STATUS_ACCEPTED).orElse(null);
        if (status == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Статус замовлення не знайдено");

        order.setOrderStatus(status);
        orderRepository.save(order);
        Order foundOrder = orderRepository.findFirstByEmailOrderByCreateDateDesc(order.getEmail());
        return ResponseEntity.ok().body(foundOrder);
    }
}
