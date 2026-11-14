package com.impact.ecommerce.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartConfig {
    private final int maxCartItems;

    public CartConfig(
            @Value("${app.max-cart-items}") int maxCartItems){
            if (maxCartItems < 1) {
                throw new IllegalArgumentException(
                        "APP_MAX_CART_ITEMS must be at least 1"
                );
            }
            this.maxCartItems = maxCartItems;
    }
    public int getMaxCartItems(){
        return maxCartItems;
    }
}
