package com.homedirect.atm.validator;

import com.homedirect.atm.model.Account;

public interface AccountValidator {

	boolean inValidUsername(String username);

	boolean inValidPassword(String password);

	boolean inValidAmount(double amount);

	boolean isValidPaymentAmount(Account account, double amount);

	boolean isvalidCreateAccount(String username, String password);
}