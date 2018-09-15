package com.homedirect.atm.controller;

import com.homedirect.atm.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.homedirect.atm.services.TransactionHistoryService;
import java.util.List;

@RestController
@RequestMapping("/history")
public class TransactionController {

	private @Autowired TransactionHistoryService transactionServive;
	
	@GetMapping("/transaction/")
	public List<Transaction> transactionHisttory(@RequestParam(value = "id", required = false) int id,
												 @RequestParam(value = "type", required = false) byte type) {
		return transactionServive.transactionType(id, type);
	}
}
