package com.hrstack.controllers;

import com.hrstack.orders.OrderProducer;
import com.hrstack.orders.ProducerMessage;
import com.hrstack.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderProducer producer;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<String>> send(@RequestBody ProducerMessage request) {
        producer.sendMessage(request);
        return ResponseEntity.ok(ApiResponse.success(true, "Message sent!", null));
    }
}