package collab.collabiz.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @Id @GeneratedValue private String title;
    private String possibleOffer; // 제공 가능한 서비스
    private String requiredOffer; //필요 서비스
    private String content; // 콜라보 상세 내용
    private LocalDateTime recruitStartDate; // 모집 시작일
    private LocalDateTime recruitEndDate; // 모집 마감일
    private String region;
    private String regionDetail; // 장소 상세 설명
    private int views; // 조회수
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;



    @Builder // 해당 클래스의 빌더패턴 클래스 생성 -> 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함 -> 빌더를 이용해 데이터 삽입
    public Post(Member member,String title, String possibleOffer,String requiredOffer, String content,LocalDateTime recruitStartDate,LocalDateTime recruitEndDate,String region,String regionDetail,List<String> keyword){
        this.member=member;
        this.title=title;
        this.possibleOffer=possibleOffer;
        this.requiredOffer=requiredOffer;
        this.content=content;
        this.recruitStartDate=recruitStartDate;
        this.recruitEndDate=recruitEndDate;
        this.region=region;
        this.regionDetail=regionDetail;
        views = 0;
    }

    /**
     * by.seongcheol
     * 등록 과정 중 파일 첨부에 관한 설계가 아직 없습니다!
     * 콜라보 등록 API를 설계하시는 분이 추가 설계를 해야할 것 같습니다.
     */
}