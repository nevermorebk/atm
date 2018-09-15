package com.homedirect.atm.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.homedirect.atm.util.ScanUtils;
import static com.homedirect.atm.commons.ConfigConstants.LENGTH_FROM;
import static com.homedirect.atm.commons.ConfigConstants.LENGTH_TO;
import static com.homedirect.atm.commons.ConfigConstants.NUMBER_ERROR;
import static com.homedirect.atm.util.StringUtils.isEmpty;

@Component
public class AccountValidatorImpl implements AccountValidator {

	@Autowired
	private ScanUtils scanInput;

	@Override
	public boolean isValidUsername(String username) {
		if (isEmpty(username) || LENGTH_FROM > username.length() || username.length() > LENGTH_TO) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidPassword(String password) {
		if (isEmpty(password) || LENGTH_FROM > password.length() || password.length() > LENGTH_TO) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isvalidAmount(double amount) {
		if (amount < 0) {
			System.out.println("Amount invalid!");
			return false;
		}

		return true;
	}

	public String inputAndCheckUsername(String username) {
		int count = 0;

		do {
			if (count == NUMBER_ERROR) {
				return null;
			}

			if (!isValidUsername(username)) {
				System.out.println("Username must have be between 3 to 15 characters or Username already exists!");
				count++;
			}
		} while (!isValidUsername(username));

		return username;
	}

	public boolean isvalidCreateAccount(String username, String password) {
		inputAndCheckUsername(username);
		if (username == null) {
			return false;
		}

		scanInput.enterPassword(password);
		if (password == null) {
			return false;
		}

		return true;
	}
}
