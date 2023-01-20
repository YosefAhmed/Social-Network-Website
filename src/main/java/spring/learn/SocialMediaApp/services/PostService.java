package spring.learn.SocialMediaApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import spring.learn.SocialMediaApp.exceptions.BadRequestException;
import spring.learn.SocialMediaApp.models.PostModel;
import spring.learn.SocialMediaApp.models.dto.AddPostRequest;
import spring.learn.SocialMediaApp.reositories.PostRepository;

import java.util.Calendar;
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
}
