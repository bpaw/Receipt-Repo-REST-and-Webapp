package rrr.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import rrr.core.models.entities.Receipt;
import rrr.rest.mvc.ReceiptController;
import rrr.rest.resources.ReceiptResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by Brandon Paw on 7/15/2017.
 */
public class ReceiptResourceAsm extends ResourceAssemblerSupport<Receipt, ReceiptResource> {

    public ReceiptResourceAsm() {
        super(ReceiptController.class, ReceiptResource.class);
    }

    @Override
    public ReceiptResource toResource(Receipt receipt) {
        ReceiptResource res = new ReceiptResource();
        res.setReceipt(receipt.getReceipt());
        res.setDate(receipt.getDate());
        res.setOwner(receipt.getOwner());
        res.setTax(receipt.getTax());
        res.setTip(receipt.getTip());
        res.setTotal(receipt.getTotal());
        res.setDescription(receipt.getDescription());
        res.setFolders(receipt.getFolders());
        res.setPhoto(receipt.getPhoto());
        res.setRid(receipt.getId());
        Link link = linkTo(ReceiptController.class).slash(receipt.getId()).withSelfRel();
        res.add(link);
        return res;
    }
}
