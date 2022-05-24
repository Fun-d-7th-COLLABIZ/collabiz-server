package collab.collabiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PROTECTED;


@Builder
@Entity
@Getter
@Setter //이후 리팩토링 예정
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
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

    @Column(nullable = false)
    private String companyName; //유저 회사 이름

    private String companyUrl; //회사 홈페이지 주소

    private String companyIntroduction ; //회사 소개글

    private String companyContactNumber; //회사전화번호

    @Column(nullable = false)
    private String businessRegistrationNumber; //사업자 등록번호

    @ColumnDefault("1")
    private String level;//프로필 레빌

    private int report;//신고 당한 횟수

    private boolean isAdmin;
}
