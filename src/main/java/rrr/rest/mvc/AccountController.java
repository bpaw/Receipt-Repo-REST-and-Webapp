package rrr.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rrr.core.models.entities.Account;
import rrr.core.models.entities.Receipt;
import rrr.core.services.AccountService;
import rrr.core.services.exceptions.AccountDoesNotExistException;
import rrr.core.services.exceptions.AccountExistsException;
import rrr.core.services.exceptions.ReceiptExistsException;
import rrr.core.services.util.AccountList;
import rrr.core.services.util.ReceiptList;
import rrr.rest.exceptions.ConflictException;
import rrr.rest.resources.AccountListResource;
import rrr.rest.resources.AccountResource;
import rrr.rest.resources.ReceiptListResource;
import rrr.rest.resources.ReceiptResource;
import rrr.rest.resources.asm.AccountListResourceAsm;
import rrr.rest.resources.asm.AccountResourceAsm;
import rrr.rest.resources.asm.ReceiptListResourceAsm;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Brandon Paw on 7/16/2017.
 */
@Controller
@RequestMapping(value = "/rest/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService service) {
        this.accountService = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount) {

        System.out.println("Username:\n" + sentAccount.getUsername() + "\nPassword:\n" + sentAccount.getPassword());
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(res, headers, HttpStatus.CREATED);
        }
        catch(AccountExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value="username", required = false) String name) {
        AccountList list = null;
        if(name == null) {
            list = accountService.findAllAccounts();
        } else {
            Account account = accountService.findByAccountName(name);
            if(account == null) {
                list = new AccountList(new ArrayList<Account>());
            } else {
                list = new AccountList(Arrays.asList(account));
            }
        }
        AccountListResource res = new AccountListResourceAsm().toResource(list);
        return new ResponseEntity<AccountListResource>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<AccountResource> getAccount(@PathVariable Long accountId) {
        Account account = accountService.findAccount(accountId);
        if (account != null) {
            AccountResource res = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/receipts_by_folder/{accountId}/{folderName}", method = RequestMethod.GET)
    public ResponseEntity<ReceiptListResource> getReceiptsByFolder(@PathVariable Long accountId, @PathVariable String folderName) {
        Account account = accountService.findAccount(accountId);
        if (account == null) {
            return new ResponseEntity<ReceiptListResource>(HttpStatus.NOT_FOUND);
        } else {
            ReceiptList recList = accountService.findReceiptsByFolder(accountId, folderName);
            ReceiptListResource resource = new ReceiptListResourceAsm().toResource(recList);
            return new ResponseEntity<ReceiptListResource>(resource, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/folders/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getFolders(@PathVariable Long accountId) {
        Account account = accountService.findAccount(accountId);
        return new ResponseEntity<>(account.parseFoldersField(), HttpStatus.OK);
    }

//    @RequestMapping(value="/{accountId}/blogs",
//            method = RequestMethod.POST)
//    public ResponseEntity<BlogResource> createBlog(
//            @PathVariable Long accountId,
//            @RequestBody BlogResource res)
//    {
//        try {
//            Blog createdBlog = accountService.createBlog(accountId, res.toBlog());
//            BlogResource createdBlogRes = new BlogResourceAsm().toResource(createdBlog);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
//            return new ResponseEntity<ReceiptResource>(createdBlogRes, headers, HttpStatus.CREATED);
//        } catch(AccountDoesNotExistException exception)
//        {
//            throw new NotFoundException(exception);
//        } catch(ReceiptExistsException exception)
//        {
//            throw new ConflictException(exception);
//        }
//    }
//
//    @RequestMapping(value="/{accountId}/blogs",
//            method = RequestMethod.GET)
////    @PreAuthorize("permitAll")
//    public ResponseEntity<BlogListResource> findAllBlogs(
//            @PathVariable Long accountId) {
//        try {
//            BlogList blogList = accountService.findBlogsByAccount(accountId);
//            BlogListResource blogListRes = new BlogListResourceAsm().toResource(blogList);
//            return new ResponseEntity<BlogListResource>(blogListRes, HttpStatus.OK);
//        } catch(AccountDoesNotExistException exception)
//        {
//            throw new NotFoundException(exception);
//        }
//    }
}
