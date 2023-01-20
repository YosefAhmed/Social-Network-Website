package spring.learn.SocialMediaApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.learn.SocialMediaApp.exceptions.BadRequestException;
import spring.learn.SocialMediaApp.models.PostModel;
import spring.learn.SocialMediaApp.models.dto.AddPostRequest;
import spring.learn.SocialMediaApp.services.PostService;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/addPost")
    public ResponseEntity<String> createPost(
            @RequestBody AddPostRequest addPostRequest
            ){
        try {
            return ResponseEntity.ok(postService.addPost(addPostRequest).toString());
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body("post content cannot be empty");
        }
    }

    public String interactWithPost(long postId){
        return null;
    }

    @GetMapping("/getPosts")
    public ResponseEntity<Iterable<PostModel>> getPosts(){
        try {
            return ResponseEntity.ok(postService.getPosts());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
