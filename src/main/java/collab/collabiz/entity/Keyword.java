package collab.collabiz.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

/**
 * 콜라보 게시글 등록 과정 중 키워드
 *
 * by.seongcheol
 */
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Keyword {

    @Id
    @GeneratedValue
    @Column(name = "keyword_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="post_Id")
    private Post post;

    private String keyword;

    @Builder
    public Keyword(Post post,String keyword) {
        this.post=post;
        this.keyword=keyword;
    }



}
