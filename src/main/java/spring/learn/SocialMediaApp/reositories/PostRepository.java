package spring.learn.SocialMediaApp.reositories;

import spring.learn.SocialMediaApp.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PostRepository extends JpaRepository<PostModel, Long>{
}
