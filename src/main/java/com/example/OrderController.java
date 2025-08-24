package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/{userId}")
    public String getOrdersByUser(@PathVariable String userId) {
        List<String> productIds = List.of("101", "102");

        StringBuilder productDetails = new StringBuilder();
        for (String productId : productIds) {
            String product = restTemplate.getForObject(
                "http://localhost:8083/products/" + productId,
                String.class
            );
            productDetails.append(product).append("\n");
        }

        return "Orders for User ID " + userId + ":\n" + productDetails;
    }


}
