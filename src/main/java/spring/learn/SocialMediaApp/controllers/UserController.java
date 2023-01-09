package spring.learn.SocialMediaApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spring.learn.SocialMediaApp.models.UserModel;
import spring.learn.SocialMediaApp.services.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public String createAccount(String name,
                                String email,
                                String password){
        return null;
    }

    public String login(String email, String password){
        return null;
    }

    public String logout(){
        return null;
    }

    public String addFriend(long friendId){

        return  null;
    }
    public Iterable<UserModel> getAllUsers(){
        return null;
    }

}
