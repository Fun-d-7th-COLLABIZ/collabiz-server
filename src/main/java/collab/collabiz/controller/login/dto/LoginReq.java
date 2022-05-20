package collab.collabiz.controller.login.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LoginReq {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
