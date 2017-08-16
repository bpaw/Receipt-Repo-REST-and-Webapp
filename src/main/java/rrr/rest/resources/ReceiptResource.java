package rrr.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import rrr.core.models.entities.Account;
import rrr.core.models.entities.Receipt;

import java.net.URL;

/**
 * Created by Brandon Paw on 7/15/2017.
 */
public class ReceiptResource extends ResourceSupport {

    private Long rid;

    private String receipt;

    private String date;

    private Account owner;

    private double tax;

    private double tip;

    private double total;

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

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
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

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
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
        receipt.setDate(getDate());
        receipt.setId(getRid());
        return receipt;
    }
}
