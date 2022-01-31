package com.aize.assignment.controllers.customer;

import com.aize.assignment.servcies.ProductService;
import com.aize.assignment.server.CustomerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/api/product")
public class ProductCustomerController extends CustomerEndpoint {
    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity getAll(Pageable pageable, @RequestParam(value = "search", required = false, defaultValue = "") String search){
        return ResponseEntity.ok(productService.findAll(pageable, search));
    }
}
