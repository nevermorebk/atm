package com.homedirect.atm.util;

import com.homedirect.atm.validator.AccountValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Scanner;
import static com.homedirect.atm.commons.ConfigConstants.NUMBER_ERROR;

@Service
public class ScanUtils {
	
	private @Autowired AccountValidatorImpl accountValidator;
	
	private Scanner scanner = new Scanner(System.in);

    public String enterString() {
        return scanner.nextLine();
    }

    public void enterId(int id ) {
    }

	public String enterUsername(String username) {
		int count = 0;
		do {
			System.out.println(" \n Please enter Username: ");
			if (count == NUMBER_ERROR) {
				return null;
			}

			if (!accountValidator.isValidUsername(username)) {
				System.out.println("Username must have be between 3 to 15 characters!");
				count++;
			}

		} while (!accountValidator.isValidUsername(username));

		return username;
	}

	public String enterPassword(String password) {
		int count = 0;
		do {
			System.out.println(" \n Please enter Password: ");
			if (count == NUMBER_ERROR) {
				return null;
			}

			if (!accountValidator.isValidPassword(password)) {
				System.out.println("Password must have be between 3 to 15 characters!");
				count++;
			}

		} while (!accountValidator.isValidPassword(password));

		return password;
	}

	public double enterAmount(double amount) {
		
		do {
			System.out.println(" \n Please enter Amount: ");

			if (!accountValidator.isvalidAmount(amount)) {
				System.out.println("Amount must more than 0!");
			}

		} while (!accountValidator.isvalidAmount(amount));

		return amount;

	}
}