package com.aize.assignment.controllers.customer;

import com.aize.assignment.controllers.messages.AddToShoppingCartRequest;
import com.aize.assignment.erros.InvalidRequestException;
import com.aize.assignment.erros.RecordNotFoundExcption;
import com.aize.assignment.erros.UnauthorizedRequestException;
import com.aize.assignment.models.Customer;
import com.aize.assignment.servcies.ShoppingCartService;
import com.aize.assignment.server.CustomerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/cart")
public class ShoppingCartController extends CustomerEndpoint {

    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity shoppingCartDetails(HttpServletRequest request){
        Customer customer = getUser(request);
        if(customer == null){
            throw  new RecordNotFoundExcption();
        }
        return ResponseEntity.ok(shoppingCartService.getCart(customer));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addToCart(@RequestBody AddToShoppingCartRequest addToShoppingCartRequest, HttpServletRequest request){
        Customer customer = getUser(request);

        if(customer == null){
            throw  new RecordNotFoundExcption();
        }

        validate(addToShoppingCartRequest);

        return ResponseEntity.ok(shoppingCartService.addToShoppingCart(addToShoppingCartRequest.getProduct(),
                addToShoppingCartRequest.getQuantity(),
                customer));
    }

    @RequestMapping(method = RequestMethod.PUT, path="/{cartItemId}")
    public ResponseEntity updateCartItem(@RequestBody AddToShoppingCartRequest addToShoppingCartRequest, @PathVariable("cartItemId") Long cartIremId, HttpServletRequest request){
        Customer customer = getUser(request);

        if(customer == null){
            throw  new UnauthorizedRequestException();
        }

        if(addToShoppingCartRequest == null) {
            throw new InvalidRequestException();
        }

        if(addToShoppingCartRequest.getQuantity() == null || addToShoppingCartRequest.getQuantity() <=0 ){
            throw new InvalidRequestException();
        }

        if(!shoppingCartService.verifyItemInCart(customer, cartIremId)){
            throw new RecordNotFoundExcption();
        }

        return ResponseEntity.ok(shoppingCartService.updateShoppingCart(cartIremId,
                addToShoppingCartRequest.getQuantity()));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteCartItem(@RequestBody AddToShoppingCartRequest[] addToShoppingCartRequests, HttpServletRequest request){
        Customer customer = getUser(request);

        if(customer == null){
            throw  new RecordNotFoundExcption();
        }

        validateDeleteRequest(addToShoppingCartRequests);

        return ResponseEntity.ok(shoppingCartService.deleteFromShoppingCart(Arrays.stream(addToShoppingCartRequests).map(shoppingCartRequest -> shoppingCartRequest.getProduct()).collect(Collectors.toSet()),
               customer));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/checkout")
    public ResponseEntity checkout(HttpServletRequest request){
        Customer customer = getUser(request);

        return ResponseEntity.ok(shoppingCartService.checkout(customer));
    }

    private void validateDeleteRequest(AddToShoppingCartRequest[] addToShoppingCartRequests) {
        if(addToShoppingCartRequests == null || addToShoppingCartRequests.length == 0){
            throw new InvalidRequestException();
        }

        for (AddToShoppingCartRequest request: addToShoppingCartRequests) {
            if(request.getProduct() == null || request.getProduct().getId() == null){
                throw new InvalidRequestException();
            }
        }
    }

    private void validate(AddToShoppingCartRequest addToShoppingCartRequest) {
        if(addToShoppingCartRequest.getQuantity() == null || addToShoppingCartRequest.getQuantity() <= 0){
            throw new InvalidRequestException();
        }

        if(addToShoppingCartRequest.getProduct() == null || addToShoppingCartRequest.getProduct().getId() == null){
            throw new InvalidRequestException();
        }
    }

}
