package com.homedirect.atm.controller;

import com.homedirect.atm.model.Account;
import com.homedirect.atm.request.AccountRequest;
import com.homedirect.atm.request.DepositRequest;
import com.homedirect.atm.request.PasswordRequest;
import com.homedirect.atm.request.TransferRequest;
import com.homedirect.atm.request.WithdrawalRequest;
import com.homedirect.atm.response.AccountResponse;
import com.homedirect.atm.services.AccountService;
import com.homedirect.atm.services.PaymentService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private @Autowired AccountService accountService;
   
    private @Autowired PaymentService paymentService;

    @PostMapping(value = "/create")
    public AccountResponse add(@RequestBody AccountRequest request) {
        return accountService.createAccount(request);
    }
    
    @PostMapping(value = "/signin")
    public AccountResponse signIn(@RequestBody AccountRequest request) {
    	return accountService.signIn(request);
    }
    
    @PostMapping(value = "/resetpassword")
    public Account changePassword(@RequestBody PasswordRequest request) {
		return accountService.changePassword(request);
    }
    
    @GetMapping(value = "/accounts/{id}")
    public AccountResponse showAccount(@PathVariable int id) {
    	return accountService.getAccountById(id);
    }
    
    @PostMapping(value = "/deposit")
    public AccountResponse deposit(@RequestBody DepositRequest request) {
		return paymentService.deposit(request);
    }
    
    @PostMapping(value = "/withdrawal")
    public AccountResponse withdrawal(@RequestBody WithdrawalRequest request) {
    	return paymentService.withdrawal(request);
    }
    
    @PostMapping(value = "/transfer")
    public AccountResponse transfer(@RequestBody TransferRequest request) {
    	return paymentService.transfer(request);
    }
}
