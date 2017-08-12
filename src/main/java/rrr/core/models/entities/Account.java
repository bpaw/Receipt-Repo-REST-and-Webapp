package rrr.core.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;
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

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFolders() {
        return folders;
    }

    public void setFolders(String folders) {
        this.folders = folders;
    }

    public List<String> parseFoldersField() {
        List<String> results = new ArrayList<String>();
        if (folders != null) {
            String substring = folders;
            int index = substring.indexOf('\n');
            int found = index;
            int pre = 0;
            while(found != -1) {
                String parse = substring.substring(pre, index);
                String rest = substring.substring(index+1, substring.length());
                results.add(parse);

                pre = index + 1;
                index = pre + rest.indexOf('\n');
                found = index - pre;
            }
        }
        return results;
    }

    public void updateFolders(List<String> inputFolders) {
        String input = "";
        for (String folder : inputFolders) {
            input += folder + '\n';
        }

        if (input.length() > 0) {
            folders = input;
        }
    }
}
