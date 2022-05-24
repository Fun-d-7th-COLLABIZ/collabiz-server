package collab.collabiz.controller.Profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileDto {
    private Resource resourceProfileImage;
    private Resource resourceBannerImage;

    private String companyName;

    private String businessRegistrationNumber;

    private String companyIntroductionSummary; //회사 소개 요약

    private String companyIntroduction ; //회사 소개글

    //고객이 업로드한 파일명
    private String uploadFileName1;
    private String uploadFileName2;
    private String uploadFileName3;

}
