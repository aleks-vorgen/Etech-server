package com.etech.controller;

import com.etech.model.OrderStatus;
import com.etech.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order-statuses")
public class OrderStatusController {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @GetMapping("all")
    public List<OrderStatus> getOrderStatusList() {
        return orderStatusRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
