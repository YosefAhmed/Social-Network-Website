package spring.learn.SocialMediaApp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.learn.SocialMediaApp.models.UserModel;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ApplicationManager {
    private UserModel currentUser;
    private String CURRENT_TOKEN;

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }

    public void setCURRENT_TOKEN(String CURRENT_TOKEN) {
        this.CURRENT_TOKEN = CURRENT_TOKEN;
    }
}