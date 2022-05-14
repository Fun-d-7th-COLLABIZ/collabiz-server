package collab.collabiz.accountInfra.validator;

import collab.collabiz.entity.account.dtos.AccountDto;
import collab.collabiz.entity.account.dtos.MailDto;
import collab.collabiz.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(AccountDto.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        MailDto form = (MailDto) object;
        if (accountRepository.existsByEmail(form.getAddress())) {
            errors.rejectValue("email","invalid email",new Object[]{form.getAddress()},"이미 사용중인 이메일입니다.");
        }
    }
}
