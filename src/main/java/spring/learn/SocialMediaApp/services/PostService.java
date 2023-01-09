package spring.learn.SocialMediaApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.learn.SocialMediaApp.reositories.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
}
