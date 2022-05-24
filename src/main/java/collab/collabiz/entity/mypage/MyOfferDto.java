package collab.collabiz.entity.mypage;

import collab.collabiz.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
//제안한 콜라보
public class MyOfferDto {
    private String email;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .build();
    }
}
