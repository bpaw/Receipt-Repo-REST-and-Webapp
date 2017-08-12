package rrr.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by Brandon Paw on 8/11/2017.
 */
public class FoldersResource extends ResourceSupport {

    private List<String> folders;

    public List<String> getFolders() {
        return folders;
    }

    public void setFolders(List<String> folders) {
        this.folders = folders;
    }
}
