package spring.learn.SocialMediaApp.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private int numberOfFriends;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany
    @JoinTable(name = "friends",
                joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "friendId", referencedColumnName = "id"))
    private Set<UserModel> listOfFriends;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString(){
        String jsonObject =
                """
                {"id": %s,"name": "%s", "email": "%s", "password": "%s", "role": "%s"}
                """;
        jsonObject = String.format(jsonObject,id, name,email, password, role);
        return jsonObject;
    }
}
