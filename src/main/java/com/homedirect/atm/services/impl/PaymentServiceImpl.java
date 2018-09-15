package com.homedirect.atm.services.impl;

import com.homedirect.atm.model.Account;
import com.homedirect.atm.model.Transaction.StatusType;
import com.homedirect.atm.model.Transaction.TransactionType;
import com.homedirect.atm.repository.AccountRepository;
import com.homedirect.atm.request.DepositRequest;
import com.homedirect.atm.request.TransferRequest;
import com.homedirect.atm.request.WithdrawalRequest;
import com.homedirect.atm.services.TransactionHistoryService;
import com.homedirect.atm.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.homedirect.atm.commons.ConfigConstants.*;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

	private @Autowired TransactionHistoryService transactionHistory;

	private @Autowired AccountRepository accountRepository;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public String deposit(DepositRequest request) {
		Optional<Account> account = accountRepository.findById(request.getId());
		account.get().setAmount(account.get().getAmount() + request.getAmount());
		System.out.println(" \n Deposit successfully! \n");

		transactionHistory.saveTransaction(TransactionType.DEPOSIT, request.getId(), NA, request.getAmount(), NO_FEE,
				TransactionType.DEPOSIT, StatusType.SUCCESS);
		return "SUCCESS";
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public String withdrawal(WithdrawalRequest request) {
		Optional<Account> account = accountRepository.findById(request.getId());
		if (isValidAmount(account.get(), request.getAmount())) {
			System.out.println(" \n Not enough balance! \n");
			transactionHistory.saveTransaction(TransactionType.WITHDRAW, request.getId(), NA, request.getAmount(), FEE,
					TransactionType.DEPOSIT, StatusType.FAILURE);
			return null;
		}

		account.get().setAmount(account.get().getAmount() - (request.getAmount() + FEE));

		System.out.println(" \n Withdrawal successfully! \n");

		transactionHistory.saveTransaction(TransactionType.WITHDRAW, request.getId(), NA, request.getAmount(), FEE,
				TransactionType.WITHDRAW, StatusType.SUCCESS);
		return "SUCCESS";
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public String transfer(TransferRequest request) {
		Optional<Account> toAccount = accountRepository.findById(request.getToId());
		Optional<Account> fromAccount = accountRepository.findById(request.getFromId());
		if (isValidAmount(fromAccount.get(), request.getAmount())) {
			System.out.println(" \n Not enough balance! \n");
			transactionHistory.saveTransaction(TransactionType.TRANSFER, request.getFromId(), request.getToId(),
					request.getAmount(), FEE, TransactionType.TRANSFER, StatusType.FAILURE);
			return "FAILURE";
		}
		
		toAccount.get().setAmount(toAccount.get().getAmount() + request.getAmount());
		fromAccount.get().setAmount(fromAccount.get().getAmount() - (request.getAmount() + FEE));

		System.out.println(" \n Transfer successfully! \n");

		transactionHistory.saveTransaction(TransactionType.TRANSFER, request.getFromId(), request.getToId(), request.getAmount(),
				FEE, TransactionType.TRANSFER, StatusType.SUCCESS);
		return "SUCCESS";
	}

	private boolean isValidAmount(Account account, double amount) {
		return (account.getAmount() - DEFAULT_AMOUNT) < amount;
	}
}
