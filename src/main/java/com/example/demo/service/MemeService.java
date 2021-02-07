package com.example.demo.service;

import com.example.demo.model.Post;

import java.util.List;

public interface MemeService {
    public Post addPost(Post post);
    public Post getPostById(int id);
    public List<Post> getAllPosts();
}
