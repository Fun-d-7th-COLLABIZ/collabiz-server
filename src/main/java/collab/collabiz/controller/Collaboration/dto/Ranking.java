package collab.collabiz.controller.Collaboration.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 콜라보 인기순위 dto
 */
@Getter @Setter
@Builder
public class Ranking {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime endDate;
    private List<String> keywords;
}
