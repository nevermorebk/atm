package com.homdirect.application.service.impl;

import com.homdirect.application.entity.Account;
import com.homdirect.application.entity.Transaction;
import com.homdirect.application.exception.ATMException;
import com.homdirect.application.repository.TransactionRepository;
import com.homdirect.application.service.AbstractService;
import com.homdirect.application.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl extends AbstractService<Transaction> implements TransactionService {

    private final AccountServiceImpl accountService;
    private final TransactionRepository repository;

    @Autowired
    public TransactionServiceImpl(AccountServiceImpl accountService, TransactionRepository repository) {
        this.accountService = accountService;
        this.repository = repository;
    }

    @Override
    public Transaction deposit(Account account, Double amount) throws ATMException {
        account.setAmount(account.getAmount() + amount);
        return saveTransaction(account.getAccountNumber(), null, amount, Transaction.Constant.STATUS_SUCCESS,
                Transaction.Constant.CONTENT_DEPOSIT, Transaction.TransactionType.DEPOSIT);
    }

    @Override
    public Transaction withdraw(Account account, Double amount) throws ATMException {
        account.setAmount(account.getAmount() - (amount + Transaction.Constant.FEE_TRANSFER));
        return saveTransaction(account.getAccountNumber(), null, amount, Transaction.Constant.STATUS_SUCCESS,
                Transaction.Constant.CONTENT_WITHDRAW, Transaction.TransactionType.WITHDRAW);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Transaction transfer(Account fromAccount, Account toAccount, Double amount, String content)
            throws ATMException {
        fromAccount.setAmount(fromAccount.getAmount() - amount - Transaction.Constant.FEE_TRANSFER);
        toAccount.setAmount(toAccount.getAmount() + amount);
        return saveTransaction(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), amount,
                Transaction.Constant.STATUS_SUCCESS, content, Transaction.TransactionType.TRANSFER);
    }

    @Override
    public Transaction saveTransaction(String fromAccountNumber, String toAccountNumber, Double transferAmount,
                                       String status, String content, Byte type) {

        Transaction transaction = new Transaction(fromAccountNumber, toAccountNumber, transferAmount,
               new Date(), status, content, type);
        return save(transaction);
    }

    @Override
    public List<Transaction> findTransactionByAccountNumber(String accountNumber) {
        return repository.findByFromAccount(accountNumber);
    }

    @Override
    public List<Transaction> findAll() {
        return repository.findAll();
    }

//	@Override
//	public Page<Transaction> search(int accountId, String fromDate, String toDate, Byte type, int pageNo, int pageSize)
//			throws ATMException {
//		Pageable pageable = null;
//		BooleanBuilder where = null;
//		try {
//			Account account = accountService.findById(accountId);
//			QTransaction transaction = QTransaction.transaction;
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			pageable = PageRequest.of(pageNo, pageSize);
//			where = new BooleanBuilder();
//			if (account.getAccountNumber() != null) {
//				where.and(transaction.fromAccount.eq(account.getAccountNumber()));
//			}
//			if (type != null) {
//				where.and(transaction.type.eq(type)
//						.and(transaction.fromAccount.likeIgnoreCase(account.getAccountNumber())));
//			}
//			if (fromDate != null && fromDate.equals(toDate)) {
//				where.and(transaction.time.goe(format.parse(fromDate)));
//				return repository.findAll(where, pageable);
//			}
//			if (fromDate != null & toDate != null) {
//				where.and(transaction.time.between(format.parse(fromDate), format.parse(toDate)));
//			}
//		} catch (ParseException e) {
//			e.getMessage();
//		}
//		return repository.findAll(where, pageable);
//	}
}