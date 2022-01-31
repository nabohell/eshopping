package com.aize.assignment.repos;

import com.aize.assignment.models.Customer;
import com.aize.assignment.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart getByCustomer(Customer customer);
}
