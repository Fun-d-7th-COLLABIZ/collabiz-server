package collab.collabiz.exception;

import collab.collabiz.controller.login.dto.LoginError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoginAdvice {

    /**
     * 로그인 검증을 통과하지 못 한 경우
     * 빈 문자열 입력 또는 일치하지 않는 이메일, 비밀번호
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(IllegalArgumentException.class)
    public LoginError loginError(IllegalArgumentException e) {
        return new LoginError(e.getMessage());
    }
}
