package collab.collabiz.controller.Account;

import collab.collabiz.entity.Account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;

@RequiredArgsConstructor
public class AccountResource extends EntityModel<Account> {
    public static EntityModel<Account> modelOf(Account account) {
        EntityModel<Account> accountResource = EntityModel.of(account);
        return accountResource;
    }
}
