package spring.learn.SocialMediaApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.learn.SocialMediaApp.reositories.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
}
