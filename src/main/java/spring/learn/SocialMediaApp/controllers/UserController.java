package spring.learn.SocialMediaApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spring.learn.SocialMediaApp.ApplicationManager;
import spring.learn.SocialMediaApp.models.UserModel;
import spring.learn.SocialMediaApp.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ApplicationManager applicationManager;
    @GetMapping("sayHello")
//    @PreAuthorize("hasRole('USER')")
    public String sayHello(){
        return "Hello from secured end point";
    }

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
