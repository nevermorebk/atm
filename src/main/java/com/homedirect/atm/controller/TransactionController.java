package com.homedirect.atm.controller;

import com.homedirect.atm.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.homedirect.atm.services.TransactionHistoryService;
import java.util.List;

@RestController
@RequestMapping("/history")
public class TransactionController {

	private @Autowired TransactionHistoryService transactionServive;
	
	@GetMapping("/transaction")
	public List<TransactionResponse> transactionHistory(@RequestParam(value = "accountid", required = false) Integer id,
												 @RequestParam(value = "type", required = false) Byte type) {
		
		return transactionServive.transactionType(id, type);
	}
}
