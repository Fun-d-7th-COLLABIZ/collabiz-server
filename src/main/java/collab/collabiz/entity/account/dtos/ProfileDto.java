package collab.collabiz.entity.account.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    //@Email(message = "이메일 형식에 맞지 않습니다.")
    //@NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    private String companyUrl; //회사 홈페이지 주소

    private String companyIntroduction ; //회사 소개글

    private String companyContactNumber; //회사전화번호
}
