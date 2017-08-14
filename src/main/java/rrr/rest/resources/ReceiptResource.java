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

    private float tax;

    private float tip;

    private float total;

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

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getTip() {
        return tip;
    }

    public void setTip(float tip) {
        this.tip = tip;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
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
