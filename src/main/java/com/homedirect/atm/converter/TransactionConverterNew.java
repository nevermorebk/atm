package com.homedirect.atm.converter;

import com.homedirect.atm.model.Transaction;
import com.homedirect.atm.response.TransactionReponse;

public interface TransactionConverterNew {

	TransactionReponse toReponse(Transaction transaction);
}
