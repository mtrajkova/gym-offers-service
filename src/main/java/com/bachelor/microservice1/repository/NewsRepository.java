package com.bachelor.microservice1.repository;

import com.bachelor.microservice1.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByGymName(String gymName);
}
