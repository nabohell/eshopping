package com.aize.assignment.controllers;

import com.aize.assignment.erros.InvalidRequestException;
import com.aize.assignment.models.News;
import com.aize.assignment.servcies.NewsService;
import com.aize.assignment.server.AdminEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.aize.assignment.controllers.messages.CreateNewsRequest;


@Controller
@RequestMapping(path = "/api/admin/news")
public class NewsAdminController extends AdminEndpoint {
    @Autowired
    NewsService newsService;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity create(@RequestBody CreateNewsRequest createNewsRequest){
        News news = validate(createNewsRequest);
        return ResponseEntity.ok(newsService.create(news));
    }

    private News validate(CreateNewsRequest createNewsRequest) {

        if(createNewsRequest == null) {
            throw new InvalidRequestException();
        }

        if(createNewsRequest.getTitle() == null || createNewsRequest.getTitle().length() == 0){
            throw new InvalidRequestException();
        }

        News news = new News();
        news.setTitle(createNewsRequest.getTitle());
        news.setDescription(createNewsRequest.getDescription());

        return news;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    ResponseEntity create(@RequestBody CreateNewsRequest createNewsRequest, @PathVariable("id") Long id){
        News news = validate(createNewsRequest);
        return ResponseEntity.ok(newsService.update(id, news));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    ResponseEntity delete( @PathVariable("id") Long id){
        newsService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity getAll(Pageable pageable, @RequestParam(required = false, name = "search", defaultValue = "") String search){
        return ResponseEntity.ok(newsService.findAll(pageable, search));
    }
}
