package com.homdirect.application.processor.impl;

import com.homdirect.application.exception.ATMException;
import com.homdirect.application.request.AccountRequest;
import com.homdirect.application.request.ChangePassRequest;
import com.homdirect.application.request.UpdateAccountRequest;
import com.homdirect.application.response.AccountResponse;

public interface AccountProcessor {

    AccountResponse login(AccountRequest request) throws ATMException;

    AccountResponse create(AccountRequest request) throws ATMException;

    AccountResponse changePassword(ChangePassRequest changePassRequest) throws ATMException;

    AccountResponse get(int id) throws ATMException;

//    void exportCsv() throws Exception;

//	Page<AccountResponse> search(SearchAccountRequest request) throws ATMException;

    AccountResponse updateAccount(UpdateAccountRequest request);

    void deleteById(int id);
}
