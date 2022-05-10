package collab.collabiz.repository.Account;

import collab.collabiz.entity.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findByEmail(String email);
    boolean existsByEmail(String email);
}
