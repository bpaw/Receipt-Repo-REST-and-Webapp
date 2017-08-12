package rrr.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import rrr.core.models.entities.Account;
import rrr.core.repositories.AccountRepo;
import rrr.core.services.util.AccountList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
@Repository
public class JpaAccountRepo implements AccountRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Account findAccountById(Long id) {
        return em.find(Account.class, id);
    }

    @Override
    public Account findAccountByName(String name) {
        Query query = em.createQuery("SELECT a FROM Account a WHERE a.username=?1");
        query.setParameter(1, name);
        List<Account> accounts = query.getResultList();
        if(accounts.size() == 0) {
            return null;
        } else {
            return accounts.get(0);
        }
    }

    @Override
    public Account findAccountByEmail(String email) {
        Query query = em.createQuery("SELECT a FROM Account a WHERE a.email=?1");
        query.setParameter(1, email);
        List<Account> accounts = query.getResultList();
        if(accounts.size() == 0) {
            return null;
        } else {
            return accounts.get(0);
        }
    }

    @Override
    public List<Account> findAllAccounts() {
        Query query = em.createQuery("SELECT a FROM Account a");
        return query.getResultList();
    }

    @Override
    public Account createAccount(Account account) {
        em.persist(account);
        return account;
    }

    @Override
    public Account updateAccount(Account update) {
        Account account = findAccountById(update.getId());
        account = update;
        em.merge(account);
        return update;
    }

    @Override
    public Account deleteAccount() {
        return null;
    }
}
