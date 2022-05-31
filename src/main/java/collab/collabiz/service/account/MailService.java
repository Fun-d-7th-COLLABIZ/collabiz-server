package collab.collabiz.service.account;

import collab.collabiz.entity.Member;
import collab.collabiz.entity.account.dtos.AccountDto;
import collab.collabiz.entity.account.dtos.AccountResponseDto;
import collab.collabiz.entity.account.dtos.MailDto;
import collab.collabiz.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class MailService {

    private JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    private static final String FROM_ADDRESS = "dahaeSpringstudy@gmail.com";
    private final MemberRepository memberRepository;

    public void simpleMailSend(HttpSession session) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo((String) session.getAttribute("email"));
        message.setFrom(MailService.FROM_ADDRESS);

        message.setSubject("CollaBiz 서비스 사용을 위해 코드를 복사하여 붙여넣어주세요.");
        message.setText("안녕하세요 CollaBiz입니다 다음 문자를 홈페이지에 입력해주세요 => " + (String) session.getAttribute("token"));


        mailSender.send(message);
    }

    public void mailSend(HttpSession session) throws UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //네이버 인증
        String host = "smtp.naver.com";
        String user = "collabiz@naver.com"; //자신의 네이버 계정
        String password = "$$collabiz";//자신의 네이버 패스워드

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // 이미지 넣기 위해 true로
            mimeMessageHelper.setTo((String) session.getAttribute("email"));
            //mimeMessageHelper.setFrom(MailService.FROM_ADDRESS);
            mimeMessageHelper.setFrom(new InternetAddress(user, "Collabiz"));
            mimeMessageHelper.setSubject("CollaBiz 서비스 사용을 위해 코드를 복사하여 붙여넣어주세요.");

            Context context = new Context(); // model에 내용담아주듯이
            context.setVariable("token",(String) session.getAttribute("token"));
            context.setVariable("username", (String) session.getAttribute("email"));
            context.setVariable("message","CollaBiz 서비스 사용을 위해 코드를 복사하여 붙여넣어주세요.");

            String message = templateEngine.process("mail/emailAuth_Template", context);

            mimeMessageHelper.setText(message,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
    }

    public String generateEmailCheckToken() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uuid = uuid.substring(0, 6);
        return uuid;
    }

    public void sendEmailCheckToken(HttpSession session) throws UnsupportedEncodingException {
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
        memberRepository.save(accountDto.toEntity());
        return accountDto.toEntity();
    }

    //
    public Boolean existsEmail(MailDto mailDto){
        return memberRepository.existsByEmail(mailDto.getAddress());
    }
}