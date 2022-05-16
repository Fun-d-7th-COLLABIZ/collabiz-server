package collab.collabiz.controller.Post.Dto;

import collab.collabiz.entity.Keyword;
import collab.collabiz.entity.Member;
import collab.collabiz.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {
    private Member member;
    private String title;
    private String possibleOffer; // 제공 가능한 서비스
    private String content; // 콜라보 상세 내용
    private LocalDateTime recruitStartDate; // 모집 시작일
    private LocalDateTime recruitEndDate; // 모집 마감일
    private String region;
    private String regionDetail; // 장소 상세 설명
    private Long id; //게시물 아이디
    private List<String> keyword; //키워드

    public Post toEntity(){
        return Post.builder()
                .member(member)
                .title(title)
                .possibleOffer(possibleOffer)
                .content(content)
                .recruitStartDate(recruitStartDate)
                .recruitEndDate(recruitEndDate)
                .region(region)
                .regionDetail(regionDetail)
                .keyword(keyword)
                .build();
    }

}
