package spring.learn.SocialMediaApp.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostInteractionRequest {
    private long postId;
    private long userId;
    private int numberOfLikes;
    private React interaction;
}
