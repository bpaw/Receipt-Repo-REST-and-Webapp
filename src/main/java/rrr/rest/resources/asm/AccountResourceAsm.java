package rrr.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import rrr.core.models.entities.Account;
import rrr.rest.mvc.AccountController;
import rrr.rest.resources.AccountResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {

    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource res = new AccountResource();
        res.setUsername(account.getUsername());
        res.setPassword(null);
        res.setEmail(account.getEmail());
        res.setRid(account.getId());
        res.setFolders(account.getFolders());
        res.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
        return res;
    }

}
