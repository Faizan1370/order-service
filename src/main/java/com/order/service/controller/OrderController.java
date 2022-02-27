package com.order.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.common.OrderRequestDTO;
import com.order.service.common.OrderResponseDTO;
import com.order.service.orderService.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/book")
    public ResponseEntity<?> bookOrder(@RequestBody OrderRequestDTO orderRequest) {
        final OrderResponseDTO orderResponseDTO = orderService.saveOrder(orderRequest);
        return ResponseEntity.ok(orderResponseDTO);

    }

}
