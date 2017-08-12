package rrr.core.services;

import rrr.core.models.entities.Account;
import rrr.core.services.util.AccountList;
import rrr.core.services.util.ReceiptList;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
public interface AccountService {

    public Account findAccount(Long id);
    public AccountList findAllAccounts();
    public Account findByAccountName(String name);
    public Account createAccount(Account account);
    public Account updateAccount();
    public Account deleteAccount();
    public ReceiptList findReceiptsByFolder(Long accoundId, String folderName);
    public ReceiptList findReceiptsByAccount(Long accountId);
}
