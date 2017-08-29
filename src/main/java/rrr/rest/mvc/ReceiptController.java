package rrr.rest.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import rrr.core.models.entities.Receipt;
import rrr.core.services.ReceiptService;
import rrr.core.services.exceptions.ReceiptNotFoundException;
import rrr.core.services.util.ReceiptList;
import rrr.rest.exceptions.NotFoundException;
import rrr.rest.resources.ReceiptListResource;
import rrr.rest.resources.ReceiptResource;
import rrr.rest.resources.asm.ReceiptListResourceAsm;
import rrr.rest.resources.asm.ReceiptResourceAsm;
import rrr.third_party.firebase.firebaseUtil;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;

/**
 * Created by Brandon Paw on 7/14/2017.
 */
@Controller
@RequestMapping("/rest/receipts/")
public class ReceiptController {

    private ReceiptService service;

    private firebaseUtil fbUtil= new firebaseUtil();

    @Autowired
    public ReceiptController(ReceiptService service) throws IOException {
        this.service = service;
    }

    @RequestMapping(value = "account/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<ReceiptListResource> findAllReceipts(@PathVariable Long accountId)
    {
        ReceiptList receiptList = service.findAllReceipts(accountId);
        ReceiptListResource recListRes = new ReceiptListResourceAsm().toResource(receiptList);
        return new ResponseEntity<ReceiptListResource>(recListRes, HttpStatus.OK);
    }

    @RequestMapping(value = "receipt/{receiptId}", method = RequestMethod.GET)
    public ResponseEntity<ReceiptResource> getReceipt(@PathVariable Long receiptId) {

        Receipt receipt = service.findReceipt(receiptId);
        if (receipt != null) {
            ReceiptResource res = new ReceiptResourceAsm().toResource(receipt);
            return new ResponseEntity<ReceiptResource>(res, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<ReceiptResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{receiptId}", method = RequestMethod.DELETE)
    public ResponseEntity<ReceiptResource> deleteReceipt(@PathVariable Long receiptId) {

        Receipt receipt = service.deleteReceipt(receiptId);
        if (receipt != null) {
            ReceiptResource res = new ReceiptResourceAsm().toResource(receipt);
            return new ResponseEntity<ReceiptResource>(res, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<ReceiptResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{receiptId}", method = RequestMethod.PUT)
    public ResponseEntity<ReceiptResource> updateReceipt(@PathVariable Long receiptId,
                                                         @RequestBody ReceiptResource update) {

        Receipt updatedReceipt = service.updateReceipt(receiptId, update.toReceipt());
        if (updatedReceipt != null) {
            ReceiptResource res = new ReceiptResourceAsm().toResource(updatedReceipt);
            return new ResponseEntity<ReceiptResource>(res, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<ReceiptResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{accountId}",
            method = RequestMethod.POST)
    public ResponseEntity<ReceiptResource> createReceipt(
            @PathVariable Long accountId,
            @RequestBody ReceiptResource sentReceipt
    ) {
        Receipt createdReceipt = null;
        try {
            if (sentReceipt.getDate() != null) {
                System.out.println(sentReceipt.getDate());
                System.out.println(sentReceipt.getDate().indexOf('T'));
                if (sentReceipt.getDate().indexOf('T') != -1)
                    sentReceipt.setDate(sentReceipt.getDate().substring(0, sentReceipt.getDate().indexOf('T')));
                System.out.println(sentReceipt.getDate());
            }
            Receipt rec = sentReceipt.toReceipt();
            createdReceipt = service.createReceipt(accountId, rec);
            if (createdReceipt.getFolders() != null && createdReceipt.getFolders().length() > 0)
                createdReceipt.setFolders(createdReceipt.getFolders().replaceAll(",", "\n") + '\n');

            boolean newFolders = createdReceipt.updateOwnersFolders(createdReceipt.parseFoldersField());

            if (newFolders)
                service.updateOwner(createdReceipt.getOwner());

            if (sentReceipt.getPhoto_bytes() != null) {
                System.out.println(sentReceipt.getPhoto_bytes());
                String cleaned = sentReceipt.getPhoto_bytes().replace("\\","");
//                byte[] encoded = Base64.getMimeDecoder().decode(cleaned);

                byte[] encoded = Base64Utils.decodeFromString(sentReceipt.getPhoto_bytes());
//                ObjectMapper mapper = new ObjectMapper();
//                byte[] encoded = mapper.convertValue(sentReceipt.getPhoto_bytes(), byte[].class);
                try {
                    String relativeLink = fbUtil.addPhoto(Long.toString(accountId), Long.toString(createdReceipt.getId()), encoded);
                    createdReceipt.setPhoto(relativeLink);
                    System.out.println("The firebase storage relative link is " + createdReceipt.getPhoto());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            ReceiptResource createdResource = new ReceiptResourceAsm().toResource(createdReceipt);
            System.out.println("createdResource has photo field of : " + createdResource.getPhoto());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdResource.getLink("self").getHref()));

//            if (createdReceipt.getPhoto() != null) {
//
//            }

            return new ResponseEntity<ReceiptResource>(createdResource, headers, HttpStatus.CREATED);
        } catch (ReceiptNotFoundException e) {
            throw new NotFoundException(e);
        }
    }
}
