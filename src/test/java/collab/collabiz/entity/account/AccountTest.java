package collab.collabiz.entity.account;

import collab.collabiz.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AccountTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void testAccount(){
        Member member = new Member();
        member.setEmail("jdahae1225@naver.com");
        Member savedMember = accountRepository.save(member);

        Member findMember = accountRepository.findByEmail(savedMember.getEmail());

        Assertions.assertThat((findMember.getEmail()).equals(member.getEmail()));


    }
}