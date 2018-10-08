package com.homdirect.application.service.impl;

import com.homdirect.application.entity.Account;
import com.homdirect.application.exception.ATMException;
import com.homdirect.application.repository.AccountRepository;
import com.homdirect.application.request.AccountRequest;
import com.homdirect.application.request.ChangePassRequest;
import com.homdirect.application.service.AbstractService;
import com.homdirect.application.service.AccountService;
import com.homdirect.application.transformer.PasswordEncryption;
import com.homdirect.application.validator.impl.Input;
import com.homdirect.application.validator.impl.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;
import java.util.Random;

@Service
public class AccountServiceImpl extends AbstractService<Account> implements AccountService {

    private final AccountRepository repository;
    private final Storage validatorStorageATM;

    @Autowired
    private AccountServiceImpl(AccountRepository accountRepository, Storage validatorStorageATM) {
        this.repository = accountRepository;
        this.validatorStorageATM = validatorStorageATM;
    }

    @Override
    public Account creatAcc(AccountRequest request) throws ATMException {
        Account newAccount = new Account();
        newAccount.setId(request.getId());
        newAccount.setAccountNumber(generateAccountNumber());
        newAccount.setUsername(request.getUsername());
        newAccount.setAmount(Account.Constant.DEFAULT_AMOUNT);
        newAccount.setPassword(PasswordEncryption.encrypt(newAccount.getPassword()));
        save(newAccount);
        return newAccount;
    }

    @Override
    public Account login(AccountRequest request) throws ATMException {
        return repository.find(request.getUsername());
    }

    @Override
    public Account changePassword(ChangePassRequest changePassRequest) throws ATMException {
        Account account = findById(changePassRequest.getId());
        Input.checkPasswordByAccount(changePassRequest.getOldPassword(), account);
        account.setPassword(PasswordEncryption.encrypt(changePassRequest.getNewPassword()));
        return account;
    }

    @Override
    public Account updateAccount(Account account, String username) {
        account.setUsername(username);
        return account;
    }

    @Override
    public void deleteAccount(Account account) {
        repository.delete(account);
    }

    public String generateAccountNumber() {
        String pattern = "22";
        Random rd = new Random();
        int max = 9999;
        int accountNumber = rd.nextInt(max);
        DecimalFormat format = new DecimalFormat("0000");
        return pattern + format.format(accountNumber);
    }

//	@Override
//	public Page<Account> search(String username, int pageNo, int pageSize) {
//		QAccount account = QAccount.account;
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		BooleanBuilder where = new BooleanBuilder();
//		if (username != null) {
//			where.and(account.username.containsIgnoreCase(username));
//		}
//		return repository.findAll(where, pageable);
//	}

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber);
    }

//	@Override
//	public Page<Account> findAll(int pageNo, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		BooleanBuilder where = new BooleanBuilder();
//		return repository.findAll(where, pageable);
//	}

//	@Override
//	public List<Account> findAlls() {
//		return findAll();
//	}

    @Override
    public Account findById(int id) {
        return validatorStorageATM.validateId(id);
    }
}