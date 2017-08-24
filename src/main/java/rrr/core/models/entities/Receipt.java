package rrr.core.models.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.persistence.Entity;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon Paw on 7/15/2017.
 */
@Entity
public class Receipt {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne @Cascade({CascadeType.MERGE})
    private Account owner;

    private String receipt;

    private String date;

    private double tax;

    private double tip;

    private double total;

    private String description;

    private String folders;

    private URL photo;

    private String photo_bytes;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhoto_bytes() {
        return photo_bytes;
    }

    public void setPhoto_bytes(String photo_bytes) {
        this.photo_bytes = photo_bytes;
    }

    public List<String> parseFoldersField() {
        List<String> results = new ArrayList<String>();
        if (folders != null) {
            String substring = folders;
            int index = substring.indexOf('\n');
            int found = index;
            int pre = 0;
            while(found != -1) {
                String parse = substring.substring(pre, index).trim();
                String rest = substring.substring(index+1, substring.length());
                System.out.println(parse);
                results.add(parse);

                pre = index + 1;
                index = pre + rest.indexOf('\n');
                found = index - pre;
            }
        }
        return results;
    }

    public boolean updateOwnersFolders(List<String> inputFolders) {
        boolean added = false;
        List<String> ownersFolders = owner.parseFoldersField();
        for (String folder : inputFolders) {
            if (!ownersFolders.contains(folder)) {
                ownersFolders.add(folder);
                added = true;
            }
        }

        if (added) {
            owner.updateFolders(ownersFolders);
            return true;
        }
        return false;
    }
}
