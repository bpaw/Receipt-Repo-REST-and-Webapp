package rrr.core.services.util;

import rrr.core.models.entities.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon Paw on 7/22/2017.
 */
public class AccountList {

    private List<Account> accounts = new ArrayList<Account>();

    public AccountList(List<Account> list) {
        this.accounts = list;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}