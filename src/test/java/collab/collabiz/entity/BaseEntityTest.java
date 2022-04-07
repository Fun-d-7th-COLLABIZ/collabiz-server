package collab.collabiz.entity;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class BaseEntityTest {

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("BaseEntity 속성 적용 테스트")
    void baseEntityTest() {
        Member member = new Member();
        em.persist(member);

        assertThat(member.getCreatedDate()).isNotNull();
        assertThat(member.getLastModifiedDate()).isNotNull();
    }
}