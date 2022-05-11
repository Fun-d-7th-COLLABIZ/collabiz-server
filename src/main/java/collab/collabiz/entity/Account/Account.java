package collab.collabiz.entity.Account;

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
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Email(message = "이메일 형식에 맞지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @JsonIgnore
    private String password;

    @NotBlank(message = "회사명은 필수 입력 값입니다.")
    private String companyName; //유저 회사 이름

    @NotBlank(message = "사업자 등록번호는 필수 입력 값입니다.")
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
