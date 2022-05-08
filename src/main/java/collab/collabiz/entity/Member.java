package collab.collabiz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.*;

/**
 * by.seongcheol
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String companyName;
    private String companyIntroduction;
    private String companyUrl;
    private String companyContactNumber;
    private String businessRegistrationNumber; // 사업자 등록 번호
    private String level;
    private int report; // 신고 누적 수
    private boolean isAdmin;
}
