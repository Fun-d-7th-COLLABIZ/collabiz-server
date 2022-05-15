package collab.collabiz.controller.login.dto;

import collab.collabiz.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRes {

    private Long id;
    private String email;
    private String companyName;
    private String level;
    private Integer report;
    private Boolean admin;
    private LocalDateTime createdDate;

    public LoginRes(Member member) {
        id = member.getId();
        email = member.getEmail();
        companyName = member.getCompanyName();
        level = member.getLevel();
        report = member.getReport();
        admin = member.isAdmin();
        createdDate = member.getCreatedDate();
    }
}
