package spring.learn.SocialMediaApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.learn.SocialMediaApp.exceptions.BadRequestException;
import spring.learn.SocialMediaApp.models.PostModel;
import spring.learn.SocialMediaApp.models.dto.AddPostRequest;
import spring.learn.SocialMediaApp.models.dto.PostInteractionRequest;
import spring.learn.SocialMediaApp.services.PostService;

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

    @PostMapping("/interactWithPost")
    public ResponseEntity<String> interactWithPost(
            @RequestBody PostInteractionRequest interactionRequest
            ){

        try {
            postService.InteractWithPost(interactionRequest);
            return ResponseEntity.ok("You liked post "+interactionRequest.getPostId());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
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
