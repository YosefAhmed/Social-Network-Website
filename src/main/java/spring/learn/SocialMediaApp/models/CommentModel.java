package spring.learn.SocialMediaApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private Calendar commentDate;
    @Column
    private String commentContent;
    @Column
    private long postId;
    @Column
    private long commenterId;
}
