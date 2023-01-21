package spring.learn.SocialMediaApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.learn.SocialMediaApp.exceptions.BadRequestException;
import spring.learn.SocialMediaApp.models.CommentModel;
import spring.learn.SocialMediaApp.models.PostModel;
import spring.learn.SocialMediaApp.models.UserModel;
import spring.learn.SocialMediaApp.models.dto.AddPostRequest;
import spring.learn.SocialMediaApp.models.dto.PostInteractionRequest;
import spring.learn.SocialMediaApp.models.dto.React;
import spring.learn.SocialMediaApp.reositories.PostRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public PostModel addPost(AddPostRequest postRequest) throws BadRequestException {
        var post = PostModel.builder()
                .content(postRequest.getPostContent())
                .postDate(Calendar.getInstance().getTime())
                .publisher(postRequest.getPublisher())
                .numberOfLikes(0)
                .numberOfComments(0)
                .listOfLikes(new HashSet<UserModel>())
                .listOfComments(new HashSet<CommentModel>())
                .build();
            if(postRequest.getPostContent() == null || postRequest.getPostContent().isEmpty()) {
                throw new BadRequestException("post content is empty!");
            }
            postRepository.save(post);

        return post;
    }

    public List<PostModel> getPosts() {
        return postRepository.findAll();
    }
    public void InteractWithPost(PostInteractionRequest interactionRequest) {
        try{
            postRepository.updateNumberOfLikesById(
                    interactionRequest.getNumberOfLikes(),
                    interactionRequest.getPostId());

            if (interactionRequest.getInteraction() == React.LIKE) {
                postRepository.addLikedUser(
                        interactionRequest.getPostId(),
                        interactionRequest.getUserId());
            } else {
                postRepository.removeLikedUser(
                        interactionRequest.getPostId(),
                        interactionRequest.getUserId()
                );
            }
        }catch (Exception e){
            throw e;
        }
    }
}
