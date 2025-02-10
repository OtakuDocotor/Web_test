package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Models.Article;

public interface Article_Repo extends JpaRepository<Article,Long> {
    
}
