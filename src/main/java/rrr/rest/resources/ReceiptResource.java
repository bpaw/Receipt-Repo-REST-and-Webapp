package rrr.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import rrr.core.models.entities.Account;
import rrr.core.models.entities.Receipt;

import java.net.URL;

/**
 * Created by Brandon Paw on 7/15/2017.
 */
public class ReceiptResource extends ResourceSupport {

    private String receipt;

    private String date;

    private Account owner;

    private int tax;

    private int tip;

    private int total;

    private String description;

    private String folders;

    private URL photo;

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public String getFolders() {
        return folders;
    }

    public void setFolders(String folders) {
        this.folders = folders;
    }

    public URL getPhoto() {
        return photo;
    }

    public void setPhoto(URL photo) {
        this.photo = photo;
    }

    public Receipt toReceipt() {
        Receipt receipt = new Receipt();
        receipt.setReceipt(getReceipt());
        receipt.setDescription(getDescription());
        receipt.setOwner(getOwner());
        receipt.setTax(getTax());
        receipt.setTip(getTip());
        receipt.setTotal(getTotal());
        receipt.setOwner(getOwner());
        receipt.setFolders(getFolders());
        receipt.setPhoto(getPhoto());
        return receipt;
    }
}
