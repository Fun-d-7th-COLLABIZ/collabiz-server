package collab.collabiz.service.Account;

import collab.collabiz.entity.Account.Account;
import collab.collabiz.entity.Account.dtos.AccountDto;
import collab.collabiz.entity.Account.dtos.AccountResponseDto;
import collab.collabiz.repository.Account.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
//@RequiredArgsConstructor
@AllArgsConstructor
public class MailService {
    //mailSender는 AllArgsConstructor가 필요함
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "dahaeSpringstudy@gmail.com";
    private AccountRepository accountRepository;

    public boolean checkEmailDuplicate(String email) {
        return accountRepository.existsByEmail(email);
    }

    public void mailSend(Account account) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(account.getEmail());
        message.setFrom(MailService.FROM_ADDRESS);

        message.setSubject("CollaBiz 서비스 사용을 위해 코드를 복사하여 붙여넣어주세요.");
        message.setText("안녕하세요 CollaBiz입니다 다음 문자를 홈페이지에 입력해주세요 => " + account.getEmailCheckToken());
        mailSender.send(message);
    }

    public void sendEmailCheckToken(Account account) {
        //1.토큰을 만들고 generateEmailCheckToken에서 Account 엔티티의 토큰에 값을 넣어준다.
        account.generateEmailCheckToken();
        //2.이메일을 보낸다
        mailSend(account);
    }

    /**
     * 이메일 토큰 검증 로직
     */
    public AccountResponseDto emailVerification(Account account, String token){
        if (!account.isValidToken(token)) {//토큰이 맞는지 검증
            return null; 
        }

        //토큰 검증이 끝났다면 -> completeSignUp으로 해당 객체의 setEmailVerified을 true로
        completeSignUp(account);
        //토큰 검증 끝난 회원을 repository에 저장
        //accountRepository.save(account);

        //소영님과 상의 후 responseDto는 안만들어도 될 듯
        return createAccountResponseDto(account);
    }
    public void completeSignUp(Account find) {
        find.completeSignUp();
    }

    public AccountResponseDto createAccountResponseDto(Account account){
        AccountResponseDto dto = new AccountResponseDto();
        dto.setEmail(account.getEmail());
        dto.setEmailVerified(account.isEmailVerified());
        return dto;
    }

    /**
     * 회원가입 정보 저장
     */
//    // save account
//    public Account saveNewAccount(Account account) {
//
//        //이메일 검증 할 때 만들어 둔 회원을 찾고 나머지 정보 채워주기
//        accountRepository.save(account);
//
//
////        //account.setEmail(accountDto.getEmail());
////        account.setPassword(accountDto.getPassword());
////        account.setCompanyName(accountDto.getCompanyName());
////        account.setCompanyNumber(accountDto.getCompanyNumber());
//
//
//
//        //map.setPassword(passwordEncoder.encode(map.getPassword()));
//        //map.generateEmailCheckToken(); 이메일 토큰 처리는 분리해 주었으니 이제 없어도 된다.
//        //Account saved = accountRepository.save(map);
//
//        //저장된 객체 반환
//        return account;
//    }
}