package spring.learn.SocialMediaApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spring.learn.SocialMediaApp.services.CommentService;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    public String commentOnPost(long postId, String commentContent){
        return null;
    }
}
