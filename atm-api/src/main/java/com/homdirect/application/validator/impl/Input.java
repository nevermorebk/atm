package com.homdirect.application.validator.impl;

import com.homdirect.application.constant.ErrorCode;
import com.homdirect.application.entity.Account;
import com.homdirect.application.entity.Transaction;
import com.homdirect.application.exception.ATMException;
import com.homdirect.application.service.AccountService;
import com.homdirect.application.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

@Component
public class Input implements InputValidator {

    private static final String USERNAME_PATTERN;
    private static final String PASSWORD_PATTERN;

    static {
        USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,15}$";
        PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
    }

    private final Storage validatorStorageATM;
    private final AccountService accountService;

    @Autowired
    public Input(Storage validatorStorageATM, AccountService accountService) {
        this.validatorStorageATM = validatorStorageATM;
        this.accountService = accountService;
    }

    public boolean validateUsername(String username) {
        return isValidUsername(username);
    }

    public boolean validatePassword(String password) {
        return isValidPassword(password);
    }

    public void validatorDeposit(Double amount) throws ATMException {
        if (amount == null) {
            throw new ATMException(ErrorCode.MISS_DATA, ErrorCode.MISS_DATA_MES);
        }
        if (amount <= 0 || amount % 10000 != 0) {
            throw new ATMException(ErrorCode.INVALID_AMOUNT_DEPOSIT, ErrorCode.INVALID_DEPOSIT_MES);
        }
    }

    public boolean validatorWithdraw(Double amount, Double oldAmount) throws ATMException {
        if (amount == null) {
            throw new ATMException(ErrorCode.MISS_DATA, ErrorCode.MISS_DATA_MES);
        }
        if (amount <= 0 || amount % 10000 != 0) {
            throw new ATMException(ErrorCode.INVALID_AMOUNT_WITHDRAW, ErrorCode.INVALID_WITHDRAW_MES);
        }
        if (amount > Transaction.Constant.MAX_AMOUNT_WITHDRAW) {
            throw new ATMException(ErrorCode.INVALID_AMOUNT, ErrorCode.INVALID_AMOUNT_MES);
        }
        if (oldAmount - amount - Transaction.Constant.FEE_TRANSFER < Transaction.Constant.DEFAULT_BALANCE) {
            throw new ATMException(ErrorCode.INVALID_AMOUNT_WITHDRAW, ErrorCode.INVALID_WITHDRAW_MES);
        }
        return true;
    }

    public boolean isValidUsername(String username) {
        return username.matches(USERNAME_PATTERN);
    }

    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_PATTERN);
    }

    public boolean isValidCreateAccount(String username, String password) throws ATMException {
        if (!validateUsername(username)) {
            throw new ATMException(ErrorCode.INVALID_INPUT_USERNAME, ErrorCode.INVALID_INPUT_USERNAME_MES);
        }
        if (!validatePassword(password)) {
            throw new ATMException(ErrorCode.INVALID_INPUT_PASSWORD, ErrorCode.INVALID_INPUT_PASWORD_MES);
        }
        if (username == null || password == null) {
            throw new ATMException(ErrorCode.MISS_DATA, ErrorCode.MISS_DATA_MES);
        }
        validatorStorageATM.checkUserNameExist(username);
        return true;
    }

    public Date getDate() {
        Date currentime = new Date();
        return new Timestamp(currentime.getTime());
    }

    public String formatAmount(Double amount) {
        DecimalFormat myFormatter = new DecimalFormat("###,###.00");
        return myFormatter.format(amount);
    }

    public Account isValidateInputTransfer(String toAccountNumber) throws ATMException {
        Account toAccount = accountService.findByAccountNumber(toAccountNumber);
        if (toAccount == null) {
            throw new ATMException(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND_MES, null);
        }
        return toAccount;
    }

    public boolean checkTransfer(Integer toId, Integer fromId) throws ATMException {
        if (toId.equals(fromId)) {
            throw new ATMException(ErrorCode.INVALID_AMOUNT_DEPOSIT, ErrorCode.DUPLICATE_INPUT_MES, toId);
        }

        if (validatorStorageATM.checkId(toId)) {
            throw new ATMException(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND_MES, toId);
        }

        if (fromId == null) {
            throw new ATMException(ErrorCode.MISS_DATA, ErrorCode.MISS_DATA_MES);
        }
        return true;
    }

    public String numberFormat(String pattern, double value) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(value);
    }

    public String setAmount(double amount) {
        String pattern = "###,###,###";
        return numberFormat(pattern, amount);
    }

    public static boolean checkPasswordByAccount(String password, Account account) {
        if (!BCrypt.checkpw(password, account.getPassword())) {
            throw new ATMException(ErrorCode.INVALID_PASSWORD, ErrorCode.INVALID_PASWORD_MES);
        }
        return true;
    }
}
