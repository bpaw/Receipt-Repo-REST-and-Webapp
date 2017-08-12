package rrr.core.services;

import rrr.core.models.entities.Account;
import rrr.core.models.entities.Receipt;
import rrr.core.services.util.ReceiptList;

/**
 * Created by Brandon Paw on 7/15/2017.
 */
public interface ReceiptService {

    public Receipt createReceipt(Long id, Receipt data);
    public Receipt findReceipt(Long id);
    public ReceiptList findAllReceipts(Long accountId);
    public Receipt deleteReceipt(Long id);
    public Receipt updateReceipt(Long id, Receipt update);
    public Account updateOwner(Account account);
}
