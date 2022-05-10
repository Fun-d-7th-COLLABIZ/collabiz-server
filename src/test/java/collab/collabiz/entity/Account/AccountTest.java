package collab.collabiz.entity.Account;

import collab.collabiz.repository.Account.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void testAccount(){
        Account account = new Account();
        account.setEmail("jdahae1225@naver.com");
        Account savedAccount = accountRepository.save(account);

        Account findAccount = accountRepository.findByEmail(savedAccount.getEmail());

        Assertions.assertThat((findAccount.getEmail()).equals(account.getEmail()));


    }
}