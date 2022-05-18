package collab.collabiz.controller.Collaboration.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 메인 페이지 콜라보 인기순위 dto
 */
@Getter @Setter
@Builder
public class Ranking {

    private Long id;
    private String title;
    private String content;
    private String heart;
    private String view;
    private String share;
    private LocalDateTime endDate;
    private List<String> keywords;
}
