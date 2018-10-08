package com.homdirect.application.validator.impl;

import com.homdirect.application.constant.ErrorCode;
import com.homdirect.application.entity.Account;
import com.homdirect.application.exception.ATMException;
import com.homdirect.application.repository.AccountRepository;
import com.homdirect.application.request.AccountRequest;
import com.homdirect.application.validator.StorageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Storage implements StorageValidator {

    private final AccountRepository repository;

    @Autowired
    public Storage(AccountRepository repository) {
        this.repository = repository;
    }

    public void checkUserNameExist(String username) {
        if (repository.find(username) != null) {
            throw new ATMException(ErrorCode.USERNAME_EXIST, ErrorCode.USERNAME_EXIST_MES, username);
        }
    }

    public boolean checkId(int id) {
        return repository.findById(id) == null;
    }

    public Account validateId(int id) {
        Optional<Account> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new ATMException(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND_MES, id);
        }

        return optional.get();
    }

    public boolean checkAccountNumbers(String accountNumber) {
        return repository.findByAccountNumber(accountNumber) == null;
    }

    public boolean checkUsername(String username) {
        Account account = repository.find(username);
        if (account == null) {
            throw new ATMException(ErrorCode.NOT_FOUND_USERNAME, ErrorCode.NOT_FOUND_USERNAME_MES, username);
        }
        return true;
    }

    public Account validateUsername(String username) {
        Account account = repository.find(username);
        if (account == null) {
            throw new ATMException(ErrorCode.NOT_FOUND_USERNAME, ErrorCode.NOT_FOUND_USERNAME_MES, username);
        }
        return account;
    }

    public Account validateLogin(String username, String password) throws ATMException {
        Account account = repository.find(username);
        if (account == null) {
            throw new ATMException(ErrorCode.NOT_FOUND_USERNAME, ErrorCode.NOT_FOUND_USERNAME_MES, username);
        }
        Input.checkPasswordByAccount(password, account);
        return account;
    }

    public void validateChangePassword(String oldPassword, String newPassword, Account account) throws ATMException {
        if (oldPassword == null || newPassword == null) {
            throw new ATMException(ErrorCode.MISS_DATA, ErrorCode.MISS_DATA_MES);
        }
        Input.checkPasswordByAccount(oldPassword, account);
        if (!Input.isValidPassword(newPassword)) {
            throw new ATMException(ErrorCode.INVALID_INPUT_PASSWORD, ErrorCode.INVALID_INPUT_PASWORD_MES);
        }
        if (newPassword.equals(oldPassword)) {
            throw new ATMException(ErrorCode.DUPLICATE_PASSWORD, ErrorCode.DUPLICATE_PASSWORD_MES);
        }
    }

    public boolean validateLogin(AccountRequest request, Account account) {
        if (account == null) {
            throw new ATMException(ErrorCode.NOT_FOUND_USERNAME, ErrorCode.NOT_FOUND_USERNAME_MES,
                    request.getUsername());
        }
        Input.checkPasswordByAccount(request.getPassword(), account);
        return true;
    }

    public static boolean validatorDeposit(Double amount) {
        if (amount == null) {
            return true;
        }
        if (amount <= 0 || amount % 10000 != 0) {
            return true;
        }
        return false;
    }
}
