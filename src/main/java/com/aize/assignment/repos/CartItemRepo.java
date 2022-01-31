package com.aize.assignment.repos;

import com.aize.assignment.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

}
