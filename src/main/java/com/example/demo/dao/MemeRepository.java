package com.example.demo.dao;

import com.example.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemeRepository extends JpaRepository<Post,Integer> {
}
