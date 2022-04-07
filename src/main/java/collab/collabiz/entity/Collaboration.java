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
}
