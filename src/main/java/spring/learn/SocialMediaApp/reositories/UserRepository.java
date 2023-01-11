package spring.learn.SocialMediaApp.reositories;

import org.springframework.security.core.userdetails.UserDetails;
import spring.learn.SocialMediaApp.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String username);
}
