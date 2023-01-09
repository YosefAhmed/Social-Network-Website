package spring.learn.SocialMediaApp.reositories;

import spring.learn.SocialMediaApp.models.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CommentRepository extends JpaRepository<CommentModel, Long> {
}
