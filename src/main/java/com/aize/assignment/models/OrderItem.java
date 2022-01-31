package com.aize.assignment.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Optional;

@Entity
public class OrderItem extends AbstractModel{
    @ManyToOne
    private Product product;
    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return Optional.of(quantity).orElse(0);
    }
}
