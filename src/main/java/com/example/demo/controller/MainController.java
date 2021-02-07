package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private MemeService memeService;

    @PostMapping("/memes")
    public Map<String, String> addPost(@RequestBody Post post)
    {
        Post addedPost = memeService.addPost(post);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(addedPost.getId()));
        return map;
    }

    @GetMapping("/memes")
    public List<Post> getAllPosts()
    {
        return memeService.getAllPosts();
    }

    @GetMapping("/memes/{id}")
    public ResponseEntity<Post> getAllPosts(@PathVariable int id)
    {
        Post post = memeService.getPostById(id);
        if(post!=null)
        {
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
