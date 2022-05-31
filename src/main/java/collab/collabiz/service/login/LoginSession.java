package collab.collabiz.service.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 세션에 들어갈 사용자 정보
 */
@Getter @Setter
@AllArgsConstructor
public class LoginSession {

    private Long id;
    private String email;
}
