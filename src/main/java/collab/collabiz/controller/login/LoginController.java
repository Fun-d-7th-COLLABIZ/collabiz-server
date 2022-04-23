package collab.collabiz.controller.login;

import collab.collabiz.controller.login.dto.LoginReq;
import collab.collabiz.controller.login.dto.LoginRes;
import collab.collabiz.controller.login.dto.LogoutRes;
import collab.collabiz.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public LoginRes login(@Validated @RequestBody LoginReq loginReq, BindingResult bindingResult, HttpServletRequest request) {
        log.info("LoginController - start");

        // 아이디 또는 비밀번호에 빈 문자열이 들어온 경우
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("아이디 비밀번호를 입력해주세요");
        }

        log.info("LoginController - end");
        return loginService.login(loginReq, request);
    }

    @GetMapping("/logout")
    public LogoutRes logout(HttpServletRequest request) {
        return loginService.logout(request);
    }
}
