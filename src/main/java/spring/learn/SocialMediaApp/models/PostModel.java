package spring.learn.SocialMediaApp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String content;
    @Column
    private Date postDate;
    @Column
    private int numberOfLikes;
    @Column
    private int numberOfComments;
    @ManyToOne
    @JoinColumn(name = "publisher", referencedColumnName = "id")
    private UserModel publisher;
    @OneToMany
    @JoinTable(name = "postComments",
                joinColumns = @JoinColumn(name = "postId", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "commentId", referencedColumnName = "id"))
    private List<CommentModel> listOfComments;
    @OneToMany
    @JoinTable(name = "postLikes",
            joinColumns = @JoinColumn(name = "postId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
    private List<UserModel> listOfLikes;

}
