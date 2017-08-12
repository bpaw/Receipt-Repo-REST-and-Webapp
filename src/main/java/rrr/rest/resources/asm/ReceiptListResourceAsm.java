package rrr.rest.resources.asm;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import rrr.core.services.util.ReceiptList;
import rrr.rest.mvc.ReceiptController;
import rrr.rest.resources.ReceiptListResource;
import rrr.rest.resources.ReceiptResource;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by Brandon Paw on 8/3/2017.
 */
public class ReceiptListResourceAsm extends ResourceAssemblerSupport<ReceiptList, ReceiptListResource> {
    public ReceiptListResourceAsm() {
        super(ReceiptController.class, ReceiptListResource.class);
    }

    @Override
    public ReceiptListResource toResource(ReceiptList list) {
        List<ReceiptResource> resources = new ReceiptResourceAsm().toResources(list.getReceipts());
        ReceiptListResource listResource = new ReceiptListResource();
        listResource.setReceipts(resources);
//      add <<  list.getAccountId()  >> back in when filtering later
        listResource.add(linkTo(methodOn(ReceiptController.class).findAllReceipts(list.getAccountId())).withSelfRel());
        return listResource;
    }
}