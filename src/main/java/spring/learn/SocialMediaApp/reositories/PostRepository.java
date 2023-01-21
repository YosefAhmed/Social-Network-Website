package spring.learn.SocialMediaApp.reositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import spring.learn.SocialMediaApp.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PostRepository extends JpaRepository<PostModel, Long>{
    @Transactional
    @Modifying
    @Query("update PostModel p set p.numberOfLikes = ?1 where p.id = ?2")
    void updateNumberOfLikesById(int numberOfLikes, long id);
    @Transactional
    @Modifying
    @Query(value = "insert into post_likes values (?1, ?2)", nativeQuery = true)
    void addLikedUser(long postId, long userId);

    @Transactional
    @Modifying
    @Query(value = "delete from post_likes p where p.post_id = ?1 and p.user_id = ?2", nativeQuery = true)
    void removeLikedUser(long postId, long userId);
}
