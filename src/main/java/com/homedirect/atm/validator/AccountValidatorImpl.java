package com.homedirect.atm.validator;

import com.homedirect.atm.model.Account;
import org.springframework.stereotype.Component;

import static com.homedirect.atm.commons.ConfigConstants.*;
import static com.homedirect.atm.util.StringUtils.isEmpty;

@Component
public class AccountValidatorImpl implements AccountValidator {

	@Override
	public boolean inValidUsername(String username) {
        return (isEmpty(username) || LENGTH_FROM > username.length() || username.length() > LENGTH_TO);
	}

	@Override
	public boolean inValidPassword(String password) {
            return (isEmpty(password) || LENGTH_FROM > password.length() || password.length() > LENGTH_TO);
	}

	public boolean inValidAmount(double amount) {
	    return amount > 0;
    }

	private String inputAndCheckUsername(String username) {
		int count = 0;

		do {
			if (count == NUMBER_ERROR) {
				return null;
			}

			if (inValidUsername(username)) {
				System.out.println("Username must have be between 3 to 15 characters or Username already exists!");
				count++;
			}
		} while (inValidUsername(username));

		return username;
	}

	public boolean isvalidCreateAccount(String username, String password) {
		inputAndCheckUsername(username);
		if (username == null) {
			return false;
		}

		checkTheNumberEnterPassword(password);
		if (password == null) {
			return false;
		}

		return true;
	}

	private String checkTheNumberEnterPassword(String password) {
		int count = 0;
		do {
			System.out.println(" \n Please enter Password: ");
			if (count == NUMBER_ERROR) {
				return null;
			}

			if (inValidPassword(password)) {
				System.out.println("Password must have be between 3 to 15 characters!");
				count++;
			}

		} while (inValidPassword(password));

		return password;
	}

    public boolean isValidPaymentAmount(Account account, double amount) {
        return (account.getAmount() - DEFAULT_AMOUNT) < amount && amount > 0;
    }
}
