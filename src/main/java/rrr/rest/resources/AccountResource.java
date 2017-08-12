package rrr.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import rrr.core.models.entities.Account;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
public class AccountResource extends ResourceSupport {

    private Long rid;
    private String username;
    private String email;
    private String password;
    private String folders;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFolders() {
        return folders;
    }

    public void setFolders(String folders) {
        this.folders = folders;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setUsername(username);
        account.setEmail(email);
        account.setPassword(null);
        account.setId(rid);
        account.setFolders(folders);
        return account;
    }

    public Long getRid() { return rid;}

    public void setRid(Long rid) {
        this.rid = rid;
    }
}
