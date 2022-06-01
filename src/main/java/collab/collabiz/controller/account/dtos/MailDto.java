package collab.collabiz.controller.account.dtos;

import collab.collabiz.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class MailDto {
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String address;

    public Member toEntity(){
        return Member.builder()
                .email(address)
                .build();
    }
}