package collab.collabiz.controller.Account;

import collab.collabiz.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;

@RequiredArgsConstructor
public class AccountResource extends EntityModel<Member> {
    public static EntityModel<Member> modelOf(Member member) {
        EntityModel<Member> accountResource = EntityModel.of(member);
        return accountResource;
    }
}
