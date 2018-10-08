package com.homdirect.application.validator;

import com.homdirect.application.entity.Account;
import com.homdirect.application.exception.ATMException;

import java.util.Date;

public interface InputValidator {

    static boolean checkPasswordByAccount(String password, Account account) {
        return true;
    }
    static boolean isValidPassword(String password){
        return true;
    }

    boolean validateUsername(String username);
    boolean validatePassword(String password);
    void validatorDeposit(Double amount) throws ATMException;
    boolean validatorWithdraw(Double amount, Double oldAmount) throws ATMException;
    boolean isValidUsername(String username);
    boolean isValidCreateAccount(String username, String password) throws ATMException;
    Date getDate();
    String formatAmount(Double amount);
    Account isValidateInputTransfer(String toAccountNumber);
    boolean checkTransfer(Integer toId, Integer fromId) throws ATMException;
    String numberFormat(String pattern, double value);
    String setAmount(double amount);

}
