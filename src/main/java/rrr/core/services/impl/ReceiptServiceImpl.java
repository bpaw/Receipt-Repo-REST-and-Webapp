package rrr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rrr.core.models.entities.Account;
import rrr.core.models.entities.Receipt;
import rrr.core.repositories.AccountRepo;
import rrr.core.repositories.ReceiptRepo;
import rrr.core.services.ReceiptService;
import rrr.core.services.exceptions.AccountDoesNotExistException;
import rrr.core.services.exceptions.ReceiptNotFoundException;
import rrr.core.services.util.ReceiptList;

import java.util.List;

/**
 * Created by Brandon Paw on 7/22/2017.
 */
@Service
@Transactional
public class ReceiptServiceImpl implements ReceiptService{

    @Autowired
    private AccountRepo accRepo;

    @Autowired
    private ReceiptRepo recRepo;

    @Override
    public Receipt createReceipt(Long accountId, Receipt data) {
        Account account = accRepo.findAccountById(accountId);
        if(account == null)
        {
            throw new AccountDoesNotExistException();
        }
        Receipt entry = recRepo.createReceipt(data);
        account.setPassword(null);
        entry.setOwner(account);
        return entry;
    }

    @Override
    public Receipt findReceipt(Long id) {
        return recRepo.findReceipt(id);
    }

    @Override
    public ReceiptList findAllReceipts(Long accountId) {
        List<Receipt> receipts = recRepo.findReceiptsByAccount(accountId);
        return new ReceiptList(accountId, receipts);
    }

    @Override
    public Receipt deleteReceipt(Long id) {
        return recRepo.deleteReceipt(id);
    }

    @Override
    public Receipt updateReceipt(Long id, Receipt data) {
        return recRepo.updateReceipt(id, data);
    }

    @Override
    public Account updateOwner(Account account) {
        return accRepo.updateAccount(account);
    }
}
