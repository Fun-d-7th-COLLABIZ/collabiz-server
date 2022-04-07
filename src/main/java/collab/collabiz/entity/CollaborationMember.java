package collab.collabiz.entity;

import collab.collabiz.entity.enumtype.CollaborationMemberRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

/**
 * 콜라보 모집 또는 지원 내역을 저장하는 엔티티
 * <p>
 * by.seongcheol
 */
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class CollaborationMember extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "collaboration_member_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "collaboration_id")
    private Collaboration collaboration;

    @Enumerated(EnumType.STRING)
    private CollaborationMemberRole role; // 제안자 또는 지원자

    private Boolean applyStatus; // 콜라보 성사 여부

    /**
     * 콜라보 성사 여부 기본값 설정
     * default : false
     */
    @PrePersist // persist 하기 전 실행
    @PreUpdate // update 쿼리 발생하기 전 실행
    public void prePersist() {
        applyStatus = applyStatus != null && applyStatus;
    }
}
