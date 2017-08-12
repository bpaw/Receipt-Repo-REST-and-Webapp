package rrr.core.repositories;

import rrr.core.models.entities.Account;
import rrr.core.services.util.AccountList;

import java.util.List;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
public interface AccountRepo {

    public Account findAccountById(Long id);
    public Account findAccountByName(String name);
    public Account findAccountByEmail(String email);
    public List<Account> findAllAccounts();
    public Account createAccount(Account account);
    public Account updateAccount(Account account);
    public Account deleteAccount();
}
