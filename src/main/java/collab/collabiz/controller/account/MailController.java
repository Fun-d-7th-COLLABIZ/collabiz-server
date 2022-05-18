package collab.collabiz.controller.account;

import collab.collabiz.accountInfra.errors.ErrorResult;
import collab.collabiz.accountInfra.errors.UserException;
import collab.collabiz.entity.Member;
import collab.collabiz.entity.account.dtos.AccountDto;
import collab.collabiz.entity.account.dtos.AccountResponseDto;
import collab.collabiz.entity.account.dtos.MailDto;
import collab.collabiz.entity.account.dtos.TokenDto;
import collab.collabiz.service.account.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@AllArgsConstructor
public class MailController {
    private final MailService mailService;

    @PostMapping("/mail")
    public ResponseEntity execMail(HttpServletRequest request, @RequestBody @Valid MailDto mailDto, Errors errors, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

       if(mailService.existsEmail(mailDto)){
            throw new UserException("이미 존재하는 이메일입니다.");
        }

        //세션 생성
        HttpSession session = request.getSession();
        String email = mailDto.getAddress();
        session.setAttribute("email", email);

        mailService.sendEmailCheckToken(session);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/emailVerification")
    public ResponseEntity emailVerification(HttpServletRequest request,@RequestBody @Valid TokenDto tokenDto,BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        AccountResponseDto dto = mailService.emailVerification(request.getSession(), tokenDto.getToken());

        //인증번호 맞지 않음
        if(dto == null){
            throw new UserException("인증번호가 맞지 않습니다.");
        } else{
            //인증이 완료 -> 세션 초기화
            request.getSession().invalidate();
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody @Valid AccountDto accountDto, BindingResult bindingResult, Errors errors) {
       if(bindingResult.hasErrors()){
           log.info("errors={}", bindingResult);
           throw new UserException("입력값이 잘못 되었습니다.");
       }
        Member member = mailService.saveNewAccount(accountDto);
        EntityModel<Member> accountResource = AccountResource.modelOf(member);

        return ResponseEntity.ok(accountResource);
    }

   @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler (UserException e) {
        ErrorResult errorResult = new ErrorResult("사용자 입력값 오류",e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

}