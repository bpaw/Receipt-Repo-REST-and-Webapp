package rrr.core.services.util;

import rrr.core.models.entities.Receipt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
public class ReceiptList {

    private List<Receipt> receipts = new ArrayList<>();
    private Long accountId;

    public ReceiptList(Long accountId, List<Receipt> receipts) { this.accountId = accountId; this.receipts = receipts;}

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
