package collab.collabiz.controller.Post.Dto;

import collab.collabiz.entity.Keyword;
import collab.collabiz.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class KeywordDto {

    private Post post;
    private String keyword;

    public Keyword toEntity() {
        return Keyword.builder()
                .post(post)
                .keyword(keyword)
                .build();
    }

    public KeywordDto(Keyword entity){ //객체를 인자로 줘서 Dto로 변환하는 생성자
        this.post = entity.getPost();
        this.keyword=entity.getKeyword();
    }
}
