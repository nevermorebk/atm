package com.homedirect.atm.services;

import com.homedirect.atm.request.DepositRequest;
import com.homedirect.atm.request.TransferRequest;
import com.homedirect.atm.request.WithdrawalRequest;

public interface PaymentService {

	String withdrawal(WithdrawalRequest request);

	String transfer(TransferRequest request);

	String deposit(DepositRequest request);
}

