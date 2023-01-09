package spring.learn.SocialMediaApp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.HashSet;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String content;
    @Column
    private Calendar postDate;
    @Column
    private int numberOfLikes;
    @Column
    private int numberOfComments;
    @ManyToOne
    @JoinColumn(name = "publisherId", referencedColumnName = "id")
    private UserModel publisherId;
    @OneToMany
    @JoinTable(name = "postComments",
                joinColumns = @JoinColumn(name = "postId", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "commentId", referencedColumnName = "id"))
    private HashSet<CommentModel> listOfComments;
    @OneToMany
    @JoinTable(name = "postLikes",
            joinColumns = @JoinColumn(name = "postId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
    private HashSet<UserModel> listOfLikes;

}
