package com.aize.assignment.models;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class ShoppingCart extends AbstractModel{

    @OneToOne
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Set<CartItem> cartItems;

    @Transient
    private Double totalPrice;

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        return cartItems != null && cartItems.size() > 0 ?
                cartItems.stream()
                        .collect(Collectors.summingDouble(value -> value.getProduct().getPrice() * value.getQuantity())) :
                Double.valueOf(0);
    }
}
