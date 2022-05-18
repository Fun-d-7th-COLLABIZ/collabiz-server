package collab.collabiz.repository;

import collab.collabiz.entity.Collaboration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CollaborationRepository extends JpaRepository<Collaboration, Long> {

    @Query("select count(h) from Heart h where h.likeCollaboration.id = :id")
    String getHeartCount(@Param("id") Long id);
}