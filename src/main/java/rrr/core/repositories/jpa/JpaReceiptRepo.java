package rrr.core.repositories.jpa;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Repository;
import rrr.core.models.entities.Account;
import rrr.core.models.entities.Receipt;
import rrr.core.repositories.ReceiptRepo;
import rrr.core.services.util.ReceiptList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Chris on 7/10/14.
 */
@Repository
public class JpaReceiptRepo implements ReceiptRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Receipt findReceipt(Long id) {
        return em.find(Receipt.class, id);
    }


    @Override
    public List<Receipt> findReceiptsByAccount(Long accountId) {
        Query query = em.createQuery("SELECT r from Receipt r where r.owner.id=?1");
        query.setParameter(1, accountId);
        return query.getResultList();
    }

    @Override
    public Receipt findReceiptByAccount(Long accountId, Receipt data) {
        Query query = em.createQuery("SELECT r from Receipt r where r.owner.id=?1 AND r?=2");
        query.setParameter(1, accountId);
        query.setParameter(2, data);
        List<Receipt> rec =  query.getResultList();
        if (rec.size() > 0)
            return rec.get(0);
        else
            return null;
    }

//    @Override
//    public Receipt findReceiptByTitle(String Title) {
//        return null;
//    }

    @Override
    public Receipt deleteReceipt(Long id) {
        Receipt entry = em.find(Receipt.class, id);
        em.remove(entry);
        return entry;
    }

    @Override
    public Receipt updateReceipt(Long id, Receipt data) {
        Receipt entry = em.find(Receipt.class, id);
        entry.setReceipt(data.getReceipt());
        entry.setTotal(data.getTotal());
        return entry;
    }

    @Override
    public Receipt createReceipt(Receipt data) {
        em.persist(data);
        return data;
    }

    @Override
    public ReceiptList findReceiptByFolder(Long accountId, String folderName) {
        Query query = em.createQuery("SELECT r FROM Receipt r where r.owner.id=?1 AND r.folders like ?2");
        query.setParameter(1, accountId);
        query.setParameter(2, "%"+folderName+"%");
        ReceiptList recList = new ReceiptList(accountId, query.getResultList());
        return recList;
    }
}