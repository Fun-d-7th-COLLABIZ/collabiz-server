package collab.collabiz.repository;

import collab.collabiz.entity.Keyword;
import collab.collabiz.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    /**
     * 게시글 키워드 조회
     */
    @Query("select k from Keyword k where k.post = :post")
    List<Keyword> findAllByPost(@Param("post") Post post);
}
