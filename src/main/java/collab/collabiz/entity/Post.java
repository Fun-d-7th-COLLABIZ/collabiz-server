package collab.collabiz.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

/**
 * 콜라보 등록 게시글
 *
 * by.seongcheol
 * 필드 정보는 기획 피그마에서 콜라보 등록 과정을 보시면 도움 될 거 같습니다.
 */
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Post extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;
    private String possibleOffer; // 제공 가능한 서비스
    private String content; // 콜라보 상세 내용
    private LocalDateTime recruitStartDate; // 모집 시작일
    private LocalDateTime recruitEndDate; // 모집 마감일
    private String region;
    private String regionDetail; // 장소 상세 설명

    /**
     * by.seongcheol
     * 등록 과정 중 파일 첨부에 관한 설계가 아직 없습니다!
     * 콜라보 등록 API를 설계하시는 분이 추가 설계를 해야할 것 같습니다.
     */
}