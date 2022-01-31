package com.aize.assignment.controllers.messages;

import com.aize.assignment.models.Customer;
import com.aize.assignment.models.Product;

public class AddToShoppingCartRequest {

    private Product product;
    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
