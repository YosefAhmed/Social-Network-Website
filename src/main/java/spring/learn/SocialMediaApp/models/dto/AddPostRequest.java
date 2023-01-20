package spring.learn.SocialMediaApp.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.learn.SocialMediaApp.models.UserModel;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddPostRequest {
    private String postContent;
    private UserModel publisher;
}
