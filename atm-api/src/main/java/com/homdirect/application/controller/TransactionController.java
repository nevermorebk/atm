package com.homdirect.application.controller;

import com.homdirect.application.processor.impl.TransactionProcessor;
import com.homdirect.application.request.DepositRequest;
import com.homdirect.application.request.TransferRequest;
import com.homdirect.application.request.WithdrawRequest;
import com.homdirect.application.response.ATMResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController extends AbstractController<TransactionProcessor> {

    @PutMapping(value = "/deposit")
    public ATMResponse<?> transactionDeposit(@RequestBody DepositRequest depositRequest) {
        return apply(depositRequest, processor::deposit);
    }

    @PutMapping(value = "/withdrawal")
    public ATMResponse<?> withdrawal(@RequestBody WithdrawRequest withdrawRequest) {
        return apply(withdrawRequest, processor::withdrawal);
    }

    @PutMapping(value = "/transfer")
    public ATMResponse<?> TransactionTransfer(@RequestBody TransferRequest transferRequest) {
        return apply(transferRequest, processor::transfer);
    }

//	@GetMapping(value = "/search")
//	public ATMResponse<?> search(@RequestParam("accountId") int id,
//			@RequestParam(value = "toDate", required = false) String toDate,
//			@RequestParam(value = "fromDate", required = false) String fromDate,
//			@RequestParam(value = "type", required = false) Byte type,
//			@RequestParam(defaultValue = "0") int pageNo) {
//		return apply(new SearchTransactionRequest(id, fromDate, toDate, type, pageNo, Transaction.Constant.PAGESIZE), processor::search);
//	}

//    @GetMapping(value = "/dowload")
//    public String dowloadCsv() throws Exception {
//        processor.exportCsv();
//        return "Dowload complete";
//    }
}
