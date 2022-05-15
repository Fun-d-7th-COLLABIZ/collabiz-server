package collab.collabiz.repository.account;

import collab.collabiz.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Member,Long> {
    Member findByEmail(String email);
    boolean existsByEmail(String email);
}
