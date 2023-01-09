package spring.learn.SocialMediaApp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private int numberOfFriends;
    @OneToMany
    @JoinTable(name = "friends",
                joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "friendId", referencedColumnName = "id"))
    private HashSet<UserModel> listOfFriends;
}
