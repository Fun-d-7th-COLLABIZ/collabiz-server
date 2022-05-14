package collab.collabiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Email(message = "이메일 형식에 맞지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

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

    @JsonIgnore
    private String password;

    @NotBlank(message = "회사명은 필수 입력 값입니다.")
    private String companyName; //유저 회사 이름


    private String companyUrl; //회사 홈페이지 주소


    private String companyIntroduction ; //회사 소개글


    private String companyContactNumber; //회사전화번호

    @NotBlank(message = "사업자 등록번호는 필수 입력 값입니다.")
    private String businessRegistrationNumber; //사업자 등록번호

    private String level;//프로필 레빌


    private int report;//신고 당한 횟수


    private String created_date;//생성 날짜


    private String last_modified_date;//수정 날짜

//    public void generateEmailCheckToken() {
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//        uuid = uuid.substring(0, 10);
//        this.emailCheckToken = uuid;
//        //this.emailCheckTokenGeneratedAt = LocalDateTime.now();
//    }
//
//    public boolean isValidToken(String token) {
//        return this.emailCheckToken.equals(token);
//    }
//
//    public void completeSignUp() {
//        this.setEmailVerified(true);
//
//    }
}
