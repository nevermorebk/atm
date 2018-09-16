package com.homedirect.atm.repository;

import com.homedirect.atm.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
    List<Transaction> getByFromAccount(int id);

    List<Transaction> getByTransactionType(byte type);

    List<Transaction> getByFromAccountAndTransactionType(int accountId, byte transactionType);
}
