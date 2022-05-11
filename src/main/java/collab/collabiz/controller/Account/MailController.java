package collab.collabiz.controller.Account;

import collab.collabiz.AccountInfra.errors.ErrorResource;
import collab.collabiz.AccountInfra.validator.SignUpFormValidator;
import collab.collabiz.entity.Account.Account;
import collab.collabiz.entity.Account.dtos.AccountDto;
import collab.collabiz.entity.Account.dtos.AccountResponseDto;
import collab.collabiz.entity.Account.dtos.MailDto;
import collab.collabiz.entity.Account.dtos.TokenDto;
import collab.collabiz.repository.Account.AccountRepository;
import collab.collabiz.service.Account.MailService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    public ResponseEntity execMail(HttpServletRequest request, @RequestBody @Valid MailDto mailDto) {
        //1.이미 있는 이메일인가 확인 if문 통과 하면 중복 없는 것
        if (mailService.checkEmailDuplicate(mailDto.getAddress())) {
            return ResponseEntity.badRequest().build(); //@@중복 Response로 바꿔주기-프론트랑 논의
        }

        HttpSession session = request.getSession();//세션 생성
        String email = mailDto.getAddress();
        session.setAttribute("email", email);


        Account account = new Account();
        account.setEmail(mailDto.getAddress());

        //시큐리티를 적용하여 @CurrentUser를 쓸 수 없기 때문에 우선 여기서 save한다.
        mailService.sendEmailCheckToken(session);
        //ccountRepository.save(account);
        return ResponseEntity.ok().build();
    }


    /**
     * 이메일 토큰 검증 로직
     */
    @PostMapping("/emailVerification")
    public ResponseEntity emailVerification(HttpServletRequest request,@RequestBody @Valid TokenDto tokenDto){

        //현재 인증 받고 있는 유저를 저장 하여 들고다닌다. @CurrentUser를 사용해서 가지고 온다.
        AccountResponseDto dto = mailService.emailVerification(request.getSession(), tokenDto.getToken());


        if(dto == null){
            return ResponseEntity.badRequest().build();
        } // 인증번호 맞지 않음

        //return ResponseEntity.ok(dto); //인증 번호 맞으면
        else{
            //인증이 완료 되었다면 세션 초기화
            request.getSession().invalidate();
            return ResponseEntity.ok().build();
        }
    }

    /**
     * 최종 회원가입
     * 0511 수정 필요;
     */

    @PostMapping("/signUp") //이메일 인증 완료 후 회원가입 완료 버튼
    public ResponseEntity signUp(@RequestBody AccountDto accountDto, Errors errors) {
        if (errors.hasErrors()) {
            EntityModel<Errors> jsr303error = ErrorResource.modelOf(errors);
            return ResponseEntity.badRequest().body(jsr303error);
        }
        //validator.validate(accountDto, errors);
        if (errors.hasErrors()) {
            EntityModel<Errors> customError = ErrorResource.modelOf(errors);
            return ResponseEntity.badRequest().body(customError);
        }
        Account account = mailService.saveNewAccount(accountDto); //회원가입(accountRepository.save())
        EntityModel<Account> accountResource = AccountResource.modelOf(account);

        //model에 담아서 전송
        return ResponseEntity.ok(accountResource);
    }
}