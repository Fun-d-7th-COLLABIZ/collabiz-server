package collab.collabiz.controller.Account;

import collab.collabiz.AccountInfra.errors.ErrorResult;
import collab.collabiz.AccountInfra.errors.UserException;
import collab.collabiz.AccountInfra.validator.SignUpFormValidator;
import collab.collabiz.entity.Member;
import collab.collabiz.entity.Account.dtos.AccountDto;
import collab.collabiz.entity.Account.dtos.AccountResponseDto;
import collab.collabiz.entity.Account.dtos.MailDto;
import collab.collabiz.entity.Account.dtos.TokenDto;
import collab.collabiz.repository.Account.AccountRepository;
import collab.collabiz.service.Account.MailService;
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
    private AccountRepository accountRepository;
    private final SignUpFormValidator validator;
    /**
     * 이메일 인증번호 전송 로직
     */
    @PostMapping("/mail")
    public ResponseEntity execMail(HttpServletRequest request, @RequestBody @Valid MailDto mailDto, Errors errors, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        //중복 이메일 검증
       if(accountRepository.existsByEmail(mailDto.getAddress())){
            throw new UserException("이미 존재하는 이메일입니다.");
        }
        HttpSession session = request.getSession();//세션 생성
        String email = mailDto.getAddress();
        session.setAttribute("email", email);


        Member member = new Member();
        member.setEmail(mailDto.getAddress());

        //시큐리티를 적용하여 @CurrentUser를 쓸 수 없기 때문에 우선 여기서 save한다.
        mailService.sendEmailCheckToken(session);
        //ccountRepository.save(account);
        return ResponseEntity.ok().build();
    }


    /**
     * 이메일 토큰 검증 로직
     */
    @PostMapping("/emailVerification")
    public ResponseEntity emailVerification(HttpServletRequest request,@RequestBody @Valid TokenDto tokenDto,BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        //현재 인증 받고 있는 유저를 저장 하여 들고다닌다. @CurrentUser를 사용해서 가지고 온다.
        AccountResponseDto dto = mailService.emailVerification(request.getSession(), tokenDto.getToken());

        if(dto == null){ //인증번호 맞지 않음
            throw new UserException("인증번호가 맞지 않습니다.");
            //return ResponseEntity.badRequest().build();
        } else{
            //인증이 완료 되었다면 세션 초기화
            request.getSession().invalidate();
            return ResponseEntity.ok().build();
        }
    }

    /**
     * 최종 회원가입
     * 0511 수정 완료;
     * 서비스 로직 분리 필요
     */

    @PostMapping("/signUp") //이메일 인증 완료 후 회원가입 완료 버튼
    public ResponseEntity signUp(@RequestBody @Valid AccountDto accountDto, BindingResult bindingResult, Errors errors) {
       if(bindingResult.hasErrors()){
           log.info("errors={}", bindingResult);
           throw new UserException("입력값이 잘못 되었습니다.");
       }
        //Account account = mailService.saveNewAccount(accountDto); //회원가입(accountRepository.save())
        Member account = new Member();
        account.setEmail(accountDto.getEmail());
        account.setPassword(accountDto.getPassword());
        account.setCompanyName(accountDto.getCompanyName());
        account.setBusinessRegistrationNumber(accountDto.getBusinessRegistrationNumber());
        accountRepository.save(account);
        EntityModel<Member> accountResource = AccountResource.modelOf(account);

        //model에 담아서 전송
        return ResponseEntity.ok(accountResource);
    }

   @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler (UserException e) {
        ErrorResult errorResult = new ErrorResult("회원가입 오류",e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

}