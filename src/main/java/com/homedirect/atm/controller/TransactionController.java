package com.homedirect.atm.controller;

import com.homedirect.atm.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.homedirect.atm.services.TransactionHistoryService;

import java.util.List;

@RestController
@RequestMapping("/history")
public class TransactionController {

	private @Autowired TransactionHistoryService transactionServive;
	
	@GetMapping("/deposit/{id}")
	public String depositHistory(@PathVariable int id) {
		return transactionServive.depositHistory(id);
	}
	
	@GetMapping("/withdrawal/{id}")
	public List<Transaction> withdrawalHistory(@PathVariable int id) {
		return transactionServive.withdrawalHistory(id);
	}
	
	@GetMapping("/transfer/{id}")
	public String transferHistory(@PathVariable int id) {
		return transactionServive.transferHistory(id);
	}
	
	@GetMapping("/receive/{id}")
	public String receiveHistory(@PathVariable int id) {
		return transactionServive.receiveHistory(id);
	}
}
