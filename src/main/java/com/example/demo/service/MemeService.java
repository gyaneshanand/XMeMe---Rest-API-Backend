package com.example.demo.service;

import com.example.demo.model.Post;

import java.util.List;
import java.util.Map;

public interface MemeService {
    public Post addPost(Post post);
    public Post getPostById(int id);
    public List<Post> getAllPosts();
    public Post updatePost(int id, Post post);
    public void deletePost(int id);
}
