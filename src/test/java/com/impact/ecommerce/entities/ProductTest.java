package com.impact.ecommerce.entities;

import org.testng.annotations.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {
    @Test
    void product_remembers_its_price(){
        // Arrange
        Product product = new Product();

        // Act
        product.setPrice(new BigDecimal("100.00"));

        // Assert
        assertEquals(new BigDecimal("100.00"), product.getPrice());
    }

}
