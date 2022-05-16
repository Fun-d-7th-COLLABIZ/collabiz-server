package collab.collabiz.service.account;

import collab.collabiz.entity.Member;
import collab.collabiz.entity.account.dtos.AccountDto;
import collab.collabiz.entity.account.dtos.AccountResponseDto;
import collab.collabiz.entity.account.dtos.MailDto;
import collab.collabiz.repository.account.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class MailService {

    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "dahaeSpringstudy@gmail.com";
    private AccountRepository accountRepository;

    public void mailSend(HttpSession session) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo((String) session.getAttribute("email"));
        message.setFrom(MailService.FROM_ADDRESS);

        message.setSubject("CollaBiz 서비스 사용을 위해 코드를 복사하여 붙여넣어주세요.");
        message.setText("안녕하세요 CollaBiz입니다 다음 문자를 홈페이지에 입력해주세요 => " + (String) session.getAttribute("token"));
        mailSender.send(message);
    }

    public String generateEmailCheckToken() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uuid = uuid.substring(0, 10);
        return uuid;
    }

    public void sendEmailCheckToken(HttpSession session) {
        session.setAttribute("token", generateEmailCheckToken());
        mailSend(session);
    }

    public boolean isValidToken(HttpSession session,String token) {return (session.getAttribute("token")).equals(token);}

    public void completeSignUp(HttpSession session) {session.setAttribute("emailVerified", true);}

    public AccountResponseDto emailVerification(HttpSession session, String token){
        if (!isValidToken(session,token)) {
            return null; 
        }
        completeSignUp(session);
        return createAccountResponseDto(session);
    }

    public AccountResponseDto createAccountResponseDto(HttpSession session){
        AccountResponseDto dto = new AccountResponseDto();
        dto.setEmail((String) session.getAttribute("email"));
        dto.setEmailVerified((Boolean)session.getAttribute("emailVerified"));
        return dto;
    }


    // save account
    public Member saveNewAccount(AccountDto accountDto) {
        accountRepository.save(accountDto.toEntity());
        return accountDto.toEntity();
    }

    //
    public Boolean existsEmail(MailDto mailDto){
        return accountRepository.existsByEmail(mailDto.getAddress());
    }
}