package com.microcompany.accountsservice.Controller;

import com.microcompany.accountsservice.model.Account;
import com.microcompany.accountsservice.model.StatusMessage;
import com.microcompany.accountsservice.persistence.AccountRepository;
import com.microcompany.accountsservice.services.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<Account>> getAll() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{aid}", method = RequestMethod.GET)
    public Account getOne(@PathVariable("aid") Long id) {
        return repo.findById(id).get();
    }

    @PutMapping("/{aid}")
    public ResponseEntity<Object> update(@PathVariable("aid") Long id, @RequestBody Account account) {
        if (id == account.getId()) {
            return new ResponseEntity<>(repo.save(account), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(new StatusMessage(HttpStatus.PRECONDITION_FAILED.value(), "Id y produt.id deben cohincidir"), HttpStatus.PRECONDITION_FAILED);
        }
    }

    @RequestMapping(value = "/{aid}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("aid") Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/All", method = RequestMethod.DELETE)
    public ResponseEntity deleteAll() {
        repo.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
