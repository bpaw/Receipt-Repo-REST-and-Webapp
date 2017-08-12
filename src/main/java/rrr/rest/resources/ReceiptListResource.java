package rrr.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by Brandon Paw on 8/3/2017.
 */
public class ReceiptListResource extends ResourceSupport {
    private List<ReceiptResource> receipts;

    public List<ReceiptResource> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<ReceiptResource> receipts) {
        this.receipts = receipts;
    }
}
