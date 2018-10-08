package com.homdirect.application.transformer;

import com.homdirect.application.entity.Transaction;
import com.homdirect.application.response.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionTransformer {

    public TransactionResponse toResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setFromAccountNumber(transaction.getFromAccount());
        response.setToAccountNumber(transaction.getToAccount());
        response.setTransferAmount(transaction.getAmount());
        response.setTime(new Date());
        response.setStatus(transaction.getStatus());
        response.setContent(transaction.getContent());
        response.setType(transaction.getType());
        return response;
    }

    public List<TransactionResponse> toResponse(List<Transaction> transactionHistories) {
        return transactionHistories.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Page<TransactionResponse> toResponse(Page<Transaction> page) {
        return page.map(transaction -> toResponse(transaction));
    }
}
