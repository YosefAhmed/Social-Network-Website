package spring.learn.SocialMediaApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.learn.SocialMediaApp.reositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
