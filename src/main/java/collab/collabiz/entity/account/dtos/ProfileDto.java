package collab.collabiz.entity.account.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private String email;

    private String companyUrl; //회사 홈페이지 주소

    private String companyIntroduction ; //회사 소개글

    private String companyContactNumber; //회사전화번호
}
