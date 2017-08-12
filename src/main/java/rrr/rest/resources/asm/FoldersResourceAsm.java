package rrr.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import rrr.rest.mvc.AccountController;
import rrr.rest.resources.FoldersResource;

/**
 * Created by Brandon Paw on 8/11/2017.
 */
public class FoldersResourceAsm extends ResourceAssemblerSupport<String, FoldersResource> {

    public FoldersResourceAsm(Class<?> controllerClass, Class<FoldersResource> resourceType) {
        super(AccountController.class, FoldersResource.class);
    }

    @Override
    public FoldersResource toResource(String s) {
        return null;
    }
}
