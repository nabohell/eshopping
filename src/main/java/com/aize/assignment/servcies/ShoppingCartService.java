package com.aize.assignment.servcies;

import com.aize.assignment.erros.CartEmptyException;
import com.aize.assignment.erros.RecordNotFoundExcption;
import com.aize.assignment.models.*;
import com.aize.assignment.repos.CartItemRepo;
import com.aize.assignment.repos.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepo shoppingCartRepo;

    @Autowired
    ProductService productService;

    @Autowired
    CartItemRepo cartItemRepo;

    @Autowired
    OrderDetailsService orderDetailsService;

    public ShoppingCart getCart(Customer customer) {
        ShoppingCart cart = shoppingCartRepo.getByCustomer(customer);
        if(cart == null){
            cart =  createNew(customer);
        }
        return cart;
    }

    private ShoppingCart createNew(Customer customer) {
        ShoppingCart cart = new ShoppingCart();
        cart.setCustomer(customer);

        return shoppingCartRepo.saveAndFlush(cart);
    }

    public ShoppingCart addToShoppingCart(Product product, Integer quantity, Customer customer) {
        ShoppingCart cart = getCart(customer);
        CartItem cartItem = new CartItem();
        product = productService.findById(product.getId());
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cart.getCartItems().add(cartItem);

        shoppingCartRepo.saveAndFlush(cart);
        return cart;
    }

    public CartItem updateShoppingCart(Long cartIremId, Integer quantity) {
        CartItem cartItem = cartItemRepo.findById(cartIremId).orElseThrow(RecordNotFoundExcption::new);
        cartItem.setQuantity(quantity);
        
        return cartItemRepo.saveAndFlush(cartItem);
    }
    
    public ShoppingCart deleteFromShoppingCart(Set<Product> products, Customer customer) {
        ShoppingCart cart = getCart(customer);
        HashSet<Product> set = new HashSet<>(products);
        cart.getCartItems().stream().forEach(cartItem -> {
            if(set.contains(cartItem.getProduct())){
                cart.getCartItems().remove(cartItem);
            }
        });

        return shoppingCartRepo.saveAndFlush(cart);
    }

    public boolean verifyItemInCart(Customer customer, Long cartIremId) {
        ShoppingCart cart = getCart(customer);
        for(CartItem item:cart.getCartItems()){
            if(cartIremId.equals(item.getId())){
                return true;
            }
        }
        return false;
    }

    public OrderDetails checkout(Customer customer) {
        ShoppingCart cart = getCart(customer);
        if(cart != null && cart.getCartItems() != null && cart.getCartItems().size() > 0){
            return orderDetailsService.createOrderDetailsFromCart(cart);
        } else {
            throw new CartEmptyException();
        }
    }
}
