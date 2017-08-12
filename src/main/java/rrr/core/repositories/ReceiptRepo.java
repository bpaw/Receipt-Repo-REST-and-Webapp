package rrr.core.repositories;

import rrr.core.models.entities.Receipt;
import rrr.core.services.util.ReceiptList;

import java.util.List;

/**
 * Created by Chris on 7/10/14.
 */
public interface ReceiptRepo {
    public Receipt findReceipt(Long id); // Returns the BlogEntry or null if it can't be found
    public List<Receipt> findReceiptsByAccount(Long accountId);
    public Receipt findReceiptByAccount(Long accountId, Receipt data);
//    public Receipt findReceiptByTitle(String Title);
    public Receipt deleteReceipt(Long id); // Deletes the found BlogEntry or returns null if it can't be found

    /**
     * @param id the id of the BlogEntry to updateBlogEntry
     * @param data the BlogEntry containing the data to be used for the updateBlogEntry
     * @return the updated BlogEntry or null if the BlogEntry with the id cannot be found
     */
    public Receipt updateReceipt(Long id, Receipt data);

    public Receipt createReceipt(Receipt data);
    public ReceiptList findReceiptByFolder(Long accountId, String folderName);
//    public List<Receipt> findByAccountId(Long blogId);
}