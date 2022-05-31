package collab.collabiz.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

/**
 * by.seongcheol
 */
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Heart {

    @Id
    @GeneratedValue
    @Column(name = "heart_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /**
     * by.seongcheol
     * 특정 콜라보레이션에 좋아요하거나 특정 회원을 좋아요하는 방식으로 생각해서
     * 데이터 저장시 아래 두 필드중 하나는 무조건 null이 될거 같습니다..
     * 더 좋은 구조가 떠오르신다면 바꿔주세요!
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "like_member_id")
    private Member likeMember; // 좋아요 표시한 회원

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "like_collaboration_id")
    private Collaboration likeCollaboration; // 좋아요 표시한 콜라보
}