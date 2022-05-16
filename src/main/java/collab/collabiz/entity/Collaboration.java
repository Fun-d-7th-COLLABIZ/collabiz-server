package collab.collabiz.entity;

import collab.collabiz.entity.enumtype.CollaborationCurrentStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.*;

/**
 * by.seongcheol
 */
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Collaboration extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "collaboration_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CollaborationCurrentStatus currentStatus; // 콜라보 현재 상황

    private LocalDateTime endDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * 높을수록 콜라보레이션 인기순위가 높아짐
     * 조회수 1 -> 1점
     * 좋아요 1 -> 10점
     * 공유 점수는 추후 추가 예정
     */
    private int point;

    public Collaboration(String name) {
        this.name = name;
        point = 0;
    }
}
