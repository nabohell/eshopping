package com.aize.assignment.controllers;

import com.aize.assignment.erros.InvalidRequestException;
import com.aize.assignment.models.Product;
import com.aize.assignment.controllers.messages.CreateProductRequest;
import com.aize.assignment.servcies.ProductService;
import com.aize.assignment.server.AdminEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/api/admin/product")
public class ProductAdminController extends AdminEndpoint {
    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity create(@RequestBody CreateProductRequest createProductRequest){
        Product product = validate(createProductRequest);
        return ResponseEntity.ok(productService.createProduct(product));
    }

    private Product validate(CreateProductRequest createProductRequest) {

        if(createProductRequest == null) {
            throw new InvalidRequestException();
        }

        if(createProductRequest.getName() == null || createProductRequest.getName().length() == 0){
            throw new InvalidRequestException();
        }

        if(createProductRequest.getPrice() == null){
            throw  new InvalidRequestException();
        }

        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setPrice(createProductRequest.getPrice());
        product.setDescription(createProductRequest.getDescription());
        return product;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    ResponseEntity create(@RequestBody CreateProductRequest createProductRequest, @PathVariable("id") Long id){
        Product product = validate(createProductRequest);
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    ResponseEntity delete( @PathVariable("id") Long id){
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity getAll(Pageable pageable, @RequestParam(value = "search", required = false, defaultValue = "") String search){
        return ResponseEntity.ok(productService.findAll(pageable, search));
    }

}
