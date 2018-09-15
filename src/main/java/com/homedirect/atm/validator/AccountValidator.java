package com.homedirect.atm.validator;

public interface AccountValidator {

	boolean isValidUsername(String username);

	boolean isValidPassword(String password);
	
	boolean isvalidAmount(double amount);
	
	boolean isvalidCreateAccount(String username, String password);
}