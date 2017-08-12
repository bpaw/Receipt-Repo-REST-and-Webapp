package rrr.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import rrr.core.services.util.AccountList;
import rrr.rest.mvc.AccountController;
import rrr.rest.resources.AccountListResource;
import rrr.rest.resources.AccountResource;

import java.util.List;

/**
 * Created by Brandon Paw on 7/23/2017.
 */
public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {


    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setAccounts(resList);
        return finalRes;
    }
}