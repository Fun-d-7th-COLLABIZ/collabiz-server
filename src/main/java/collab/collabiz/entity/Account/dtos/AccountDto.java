package collab.collabiz.entity.Account.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotBlank(message = "패스워드 값은 필수 입력 값입니다.")
    @Length(min = 8,max = 40)
    //@JsonIgnore
    private String password;

    @NotBlank(message = "회사명은 필수 입력 값입니다.")
    //@Length(min = 3,max = 20)
    //@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String companyName;

    @NotBlank(message = "사업자 등록번호는 필수 입력 값입니다.")
    //@Length(min = 3,max = 20)
    //@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String businessRegistrationNumber;

}
