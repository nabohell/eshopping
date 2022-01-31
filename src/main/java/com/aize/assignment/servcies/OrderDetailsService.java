package com.aize.assignment.servcies;

import com.aize.assignment.models.*;
import com.aize.assignment.repos.OrderDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderDetailsService {

    @Autowired
    OrderDetailsRepo orderDetailsRepo;

   public OrderDetails createOrderDetailsFromCart(ShoppingCart cart){
       OrderDetails orderDetails = new OrderDetails();
       orderDetails.setCustomer(cart.getCustomer());
       orderDetails.setPaymentCode(cart.getCustomer().getPaymentCode());
       orderDetails.setStatus(OrderStatus.CHECKOUT);
       orderDetails.setTotalPrice(cart.getTotalPrice());
       orderDetails.setOrderItems(cart
               .getCartItems()
               .stream()
               .map(cartItem -> createOrderItemFromCartItem(cartItem))
               .collect(Collectors.toSet()));

       return orderDetailsRepo.saveAndFlush(orderDetails);
   }

    private OrderItem createOrderItemFromCartItem(CartItem cartItem) {
       OrderItem orderItem = new OrderItem();
       orderItem.setProduct(cartItem.getProduct());
       orderItem.setQuantity(cartItem.getQuantity());

       return orderItem;
    }
}
