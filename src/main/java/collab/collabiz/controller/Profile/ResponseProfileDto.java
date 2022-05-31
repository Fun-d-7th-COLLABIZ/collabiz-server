package collab.collabiz.controller.Profile;

import collab.collabiz.entity.Member;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseProfileDto {

    //@Lob //binary
    //private String resourceProfileImage;
    //@Lob
   //private String resourceBannerImage;

    private String companyName;

    private String faxNumber; //fax주소
    private String companyUrl; //회사 홈페이지 주소
    
    private String businessRegistrationNumber; //사업자 등록 번호

    private String companyIntroductionSummary; //회사 소개 요약

    private String companyIntroduction ; //회사 소개글

    private String email;
    private String level;//프로필 레빌
    private String companyContactNumber; //회사전화번호

    private String region;
    private String regionDetail; // 상세 주소

    //고객이 업로드한 파일명
    private String uploadFileName1;
    private String uploadFileName2;
    private String uploadFileName3;

    public ResponseProfileDto(Member member) {
        this.companyName = companyName;
        this.faxNumber = faxNumber;
        this.companyUrl = companyUrl;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.companyIntroductionSummary = companyIntroductionSummary;
        this.companyIntroduction = companyIntroduction;
        this.email = email;
        this.level = level;
        this.companyContactNumber = companyContactNumber;
        this.region = region;
        this.regionDetail = regionDetail;
        this.uploadFileName1 = uploadFileName1;
        this.uploadFileName2 = uploadFileName2;
        this.uploadFileName3 = uploadFileName3;
    }
}
