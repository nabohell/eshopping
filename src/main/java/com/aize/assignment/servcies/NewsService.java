package com.aize.assignment.servcies;

import com.aize.assignment.erros.RecordNotFoundExcption;
import com.aize.assignment.models.News;
import com.aize.assignment.models.Product;
import com.aize.assignment.repos.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;

    public News create(News newsItem) {
        return newsRepository.saveAndFlush(newsItem);
    }

    public News update(Long id, News newsItem) {
        News news = newsRepository.findById(id).orElseThrow(RecordNotFoundExcption::new);
        news.setTitle(newsItem.getTitle());
        news.setDescription(newsItem.getDescription());
        news.setUpdatedDate(new Date());

        return newsRepository.saveAndFlush(news);
    }

    public void delete(Long id) {
        newsRepository.deleteById(id);
    }

    public Page findAll(Pageable pageable, String title) {
        return newsRepository.findAllByTitleContainsOrderByCreatedDate(title, pageable);
    }
}
