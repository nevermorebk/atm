package com.homdirect.application.validator;

import com.homdirect.application.entity.Account;
import com.homdirect.application.exception.ATMException;
import com.homdirect.application.request.AccountRequest;

public interface StorageValidator {

    static boolean validatorDeposit(Double amount) {
        return true;
    }
    boolean checkId(int id);
    Account validateId(int id);
    boolean checkAccountNumbers(String accountNumber);
    boolean checkUsername(String username);
    Account validateUsername(String username);
    Account validateLogin(String username, String password) throws ATMException;
    void validateChangePassword(String oldPassword, String newPassword, Account account) throws ATMException;
    boolean validateLogin(AccountRequest request, Account account);
}
