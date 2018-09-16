package com.homedirect.atm.transformer;

import com.homedirect.atm.model.Account;
import com.homedirect.atm.request.AccountRequest;
import com.homedirect.atm.response.AccountResponse;

public interface AccountTransformer {

    Account toAccount(AccountRequest request);

    AccountResponse toResponse(Account account);

}
