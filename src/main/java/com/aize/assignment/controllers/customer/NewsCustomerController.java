package com.aize.assignment.controllers.customer;

import com.aize.assignment.servcies.NewsService;
import com.aize.assignment.server.CustomerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/api/news")
public class NewsCustomerController extends CustomerEndpoint {

    @Autowired
    NewsService newsService;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity getAll(Pageable pageable, @RequestParam(required = false, name = "search", defaultValue = "") String search){
        return ResponseEntity.ok(newsService.findAll(pageable, search));
    }
}
