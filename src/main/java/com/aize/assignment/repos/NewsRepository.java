package com.aize.assignment.repos;

import com.aize.assignment.models.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Page findAllByTitleContainsOrderByCreatedDate(String title, Pageable pageable);
}
