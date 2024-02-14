package com.microcompany.accountsservice.Controller;

import com.microcompany.accountsservice.model.Account;
import com.microcompany.accountsservice.persistence.AccountRepository;
import com.microcompany.accountsservice.services.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private IAccountService service;

    @Autowired
    private AccountRepository repo;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Account> save(@RequestBody Account newAcc) {
        logger.info("newAcount:" + newAcc.toString());
        return new ResponseEntity<>(repo.save(newAcc), HttpStatus.CREATED);
    }



}
