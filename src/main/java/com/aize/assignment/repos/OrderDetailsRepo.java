package com.aize.assignment.repos;

import com.aize.assignment.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Long> {
}
