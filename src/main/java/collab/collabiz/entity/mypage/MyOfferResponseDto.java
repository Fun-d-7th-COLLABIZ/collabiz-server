package collab.collabiz.entity.mypage;

import collab.collabiz.entity.Member;
import collab.collabiz.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyOfferResponseDto {
    private Long id;
    private Member member;
    private String title;
    private String possibleOffer; // 제공 가능한 서비스
    private String content; // 콜라보 상세 내용
    private LocalDateTime recruitStartDate; // 모집 시작일
    private LocalDateTime recruitEndDate; // 모집 마감일
    private String region;
    private String regionDetail; // 장소 상세 설명

    public MyOfferResponseDto(Post entity) {

        this.id=entity.getId();
        this.member = entity.getMember();
        this.title = entity.getTitle();
        this.possibleOffer = entity.getPossibleOffer();
        this.content = entity.getContent();
        this.recruitStartDate = entity.getRecruitStartDate();
        this.recruitEndDate = entity.getRecruitEndDate();
        this.region = entity.getRegion();
        this.regionDetail = entity.getRegionDetail();

    }
}
