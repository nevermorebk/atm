package com.homedirect.atm.services;

import com.homedirect.atm.request.DepositRequest;
import com.homedirect.atm.request.TransferRequest;
import com.homedirect.atm.request.WithdrawalRequest;
import com.homedirect.atm.response.AccountResponse;

public interface PaymentService {

	AccountResponse withdrawal(WithdrawalRequest request);

	AccountResponse transfer(TransferRequest request);

	AccountResponse deposit(DepositRequest request);
}

