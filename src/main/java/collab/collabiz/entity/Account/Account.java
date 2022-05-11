package collab.collabiz.entity.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Email
    private String email;

    @JsonIgnore
    private String password;

    private String companyName; //유저 회사 이름

    private String companyNumber; //사업자 등록번호

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
