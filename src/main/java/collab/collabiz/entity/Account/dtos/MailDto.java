package collab.collabiz.entity.Account.dtos;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class MailDto {
    @Email(message = "\"이메일 형식에 맞지 않습니다.\"")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String address;
    //private String title;
    //private String message;
}