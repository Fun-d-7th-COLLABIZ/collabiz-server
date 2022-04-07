package collab.collabiz.repository;

import collab.collabiz.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * by.seongcheol
 * 모두 사용해보셔서 알고 계시겠지만 혹시나 하는 마음에
 * jpql 직접 작성이 필요한 경우 이렇게 사용하시면 됩니다!
 *
 * @Query("select m from Member m where m.username = :username and m.age = :age")
 * List<Member> findUser(@Param("username") String username, @Param("age") int age);
 */
public interface MemberRepository extends JpaRepository<Member, Long> {


}
