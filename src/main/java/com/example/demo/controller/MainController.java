package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
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

    @PatchMapping("/memes/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable int id,
                                           @RequestBody Map<String, Object> fields)
    {
        Post post = memeService.getPostById(id);
        if( post == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Claim object does not exist
        }
        fields.remove("id");   // Remove id from request, we don't ever want to change the id.
        fields.remove("name"); // Remove name from request, we don't ever want to change the id.
        fields.forEach((k, v) -> {
            // use reflection to get field k on object and set it to value v
            Field field = ReflectionUtils.findField(Post.class, k); // find field in the Post class
            field.setAccessible(true);
            ReflectionUtils.setField(field, post, v); // set given field for defined object to value V
        });
        memeService.updatePost(id,post);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
