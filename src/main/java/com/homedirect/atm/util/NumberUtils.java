package com.homedirect.atm.util;

import java.text.DecimalFormat;

public class NumberUtils {

	private static String numberFormat(String pattern, double value) {
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		return decimalFormat.format(value);
	}

	public static String formatAmount(double amount) {
		String pattern = "###,###,###";
		return numberFormat(pattern, amount);
	}
}
