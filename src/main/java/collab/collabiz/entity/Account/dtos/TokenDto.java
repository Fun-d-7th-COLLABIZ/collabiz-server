package collab.collabiz.entity.Account.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
public class TokenDto {
    @NotBlank(message = "인증번호를 입력해주세요")
    private String token;
}
