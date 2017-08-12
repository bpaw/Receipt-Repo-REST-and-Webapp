package rrr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rrr.core.models.entities.Account;
import rrr.core.models.entities.Receipt;
import rrr.core.repositories.AccountRepo;
import rrr.core.repositories.ReceiptRepo;
import rrr.core.services.AccountService;
import rrr.core.services.exceptions.AccountDoesNotExistException;
import rrr.core.services.exceptions.AccountExistsException;
import rrr.core.services.exceptions.ReceiptExistsException;
import rrr.core.services.util.AccountList;
import rrr.core.services.util.ReceiptList;

import java.util.List;

/**
 * Created by Chris on 7/10/14.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ReceiptRepo receiptRepo;

    @Override
    public Account findAccount(Long id) {
        return accountRepo.findAccountById(id);
    }

    @Override
    public Account createAccount(Account data) {
        Account accountByUsername = accountRepo.findAccountByName(data.getUsername());
        Account accountByEmail = accountRepo.findAccountByEmail(data.getEmail());
        if(accountByUsername != null)
        {
            throw new AccountExistsException("It seems that there is already an account with the username you entered.");
        }
        if (accountByEmail != null) {
            throw new AccountExistsException("It seems there already exists an account with the email you entered.");
        }
        return accountRepo.createAccount(data);
    }

    @Override
    public Account updateAccount() {
        return null;
    }

    @Override
    public Account deleteAccount() {
        return null;
    }

//    @Override
//    public Receipt createBlog(Long accountId, Receipt data) {
//        Receipt blogSameTitle = receiptRepo.findBlogByTitle(data.getReceipt());
//
//        if(blogSameTitle != null)
//        {
//            throw new ReceiptExistsException();
//        }
//
//        Account account = accountRepo.findAccountById(accountId);
//        if(account == null)
//        {
//            throw new AccountDoesNotExistException();
//        }
//
//        Receipt createdBlog = receiptRepo.createReceipt(data);
//
//        createdBlog.setOwner(account);
//
//        return createdBlog;
//    }

    @Override
    public ReceiptList findReceiptsByAccount(Long accountId) {
        Account account = accountRepo.findAccountById(accountId);
        if(account == null)
        {
            throw new AccountDoesNotExistException();
        }
        return new ReceiptList(accountId, receiptRepo.findReceiptsByAccount(accountId));
    }

    @Override
    public ReceiptList findReceiptsByFolder(Long accoundId, String folderName) {
        return receiptRepo.findReceiptByFolder(accoundId, folderName);
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepo.findAllAccounts());
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepo.findAccountByName(name);
    }
}