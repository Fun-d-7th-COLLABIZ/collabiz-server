package collab.collabiz.repository;

import collab.collabiz.entity.Member;
import collab.collabiz.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Optional<Post> findByEmail(String email);
}