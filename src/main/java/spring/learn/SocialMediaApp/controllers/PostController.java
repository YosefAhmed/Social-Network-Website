package spring.learn.SocialMediaApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.learn.SocialMediaApp.models.PostModel;
import spring.learn.SocialMediaApp.services.PostService;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @GetMapping("/home")
    @ResponseBody
    public String getHomePage(){
        return "home";
    }

    public String createPost(String content){
        return null;
    }

    public String interactWithPost(long postId){
        return null;
    }

    public Iterable<PostModel> getPosts(){
        return null;
    }
}
