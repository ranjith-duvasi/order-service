package com.example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Value("${products.service.baseurl}")
    private String productsServiceBaseurl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/{userId}")
    public String getOrdersByUser(@PathVariable String userId) {
        List<String> productIds = List.of("101", "102");

        StringBuilder productDetails = new StringBuilder();
        logger.info("Fetching product details for user {} from products service {}", userId, productsServiceBaseurl);
        for (String productId : productIds) {
            String product = restTemplate.getForObject(
                productsServiceBaseurl + productId,
                String.class
            );
            productDetails.append(product).append("\n");
        }
        logger.info("Completed fetching product details for user: {}", userId);

        return "Orders for User ID " + userId + ":\n" + productDetails;
    }


}
