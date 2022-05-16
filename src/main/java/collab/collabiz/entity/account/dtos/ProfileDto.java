package collab.collabiz.entity.account.dtos;

import collab.collabiz.entity.Member;
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

    private String businessRegistrationNumber;//사업자 등록번호


    //고객 프로필 이미지
    private String uploadProfileImage;
    private String storeProfileImage;

    //고객 배너 이미지
    private String uploadBannerImage;
    private String storeBannerImage;

    //고객이 업로드한 파일명
    private String uploadFileName1;
    private String uploadFileName2;
    private String uploadFileName3;

    //서버 내부에서 관리하는 파일명
    private String storeFileName1;
    private String storeFileName2;
    private String storeFileName3;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .companyUrl(companyUrl)
                .companyIntroduction(companyIntroduction)
                .companyContactNumber(companyContactNumber)
                .uploadProfileImage(uploadProfileImage)
                .storeProfileImage(storeProfileImage)
                .uploadBannerImage(uploadBannerImage)
                .storeBannerImage(storeBannerImage)
                .uploadFileName1(uploadFileName1)
                .storeFileName1(storeFileName1)
                .uploadFileName1(uploadFileName2)
                .storeFileName1(storeFileName2)
                .uploadFileName1(uploadFileName3)
                .storeFileName1(storeFileName3)
                .build();
    }
}
