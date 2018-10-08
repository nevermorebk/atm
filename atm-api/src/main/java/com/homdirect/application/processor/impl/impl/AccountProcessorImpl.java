package com.homdirect.application.processor.impl.impl;

import com.homdirect.application.entity.Account;
import com.homdirect.application.exception.ATMException;
import com.homdirect.application.processor.impl.AccountProcessor;
import com.homdirect.application.request.AccountRequest;
import com.homdirect.application.request.ChangePassRequest;
import com.homdirect.application.request.UpdateAccountRequest;
import com.homdirect.application.response.AccountResponse;
import com.homdirect.application.service.AccountService;
import com.homdirect.application.transformer.AccountTransformer;
import com.homdirect.application.validator.impl.Input;
import com.homdirect.application.validator.impl.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountProcessorImpl implements AccountProcessor {

    private final AccountService service;
    private final AccountTransformer transformer;
    private final Input atmInputValidtor;
    private final Storage storageValidtor;

    @Autowired
    public AccountProcessorImpl(AccountService service, AccountTransformer transformer, Input atmInputValidtor, Storage storageValidtor) {
        this.service = service;
        this.transformer = transformer;
        this.atmInputValidtor = atmInputValidtor;
        this.storageValidtor = storageValidtor;
    }

    @Override
    public AccountResponse login(AccountRequest request) throws ATMException {
        Account account = service.login(request);
        return transformer.toResponse(account);
    }

    public AccountResponse create(AccountRequest request) throws ATMException {
        atmInputValidtor.isValidCreateAccount(request.getUsername(), request.getPassword());
        Account account = service.creatAcc(request);
        service.save(account);
        return transformer.toResponse(account);
    }

//	public Page<AccountResponse> findAll(PageRequest request) {
//		Page<Account> accounts = service.findAll(request.getPageNo(), request.getPageSize());
//		return transformer.toResponse(accounts);
//	}
//
//	public List<Account> findAll() {
//		return service.findAlls();
//	}

    @Override
    public AccountResponse get(int id) throws ATMException {
        Account account = service.findById(id);
        return transformer.toResponse(account);
    }

    @Override
    public AccountResponse changePassword(ChangePassRequest changePassRequest) throws ATMException {
        Account account = service.findById(changePassRequest.getId());
        storageValidtor.validateChangePassword(changePassRequest.getOldPassword(), changePassRequest.getNewPassword(), account);
        Account saveAccont = service.changePassword(changePassRequest);
        service.save(saveAccont);
        return transformer.toResponse(account);
    }

//	@Override
//	public Page<AccountResponse> search(SearchAccountRequest request) throws ATMException {
//		return transformer
//				.toResponse(service.search(request.getUsername(), request.getPageNo(), request.getPageSize()));
//	}

    @Override
    public AccountResponse updateAccount(UpdateAccountRequest request) {
        Account account = service.findById(request.getId());
        storageValidtor.checkUserNameExist(request.getUsername());
        Account updateAccount = service.updateAccount(account, request.getUsername());
        service.save(updateAccount);
        return transformer.toResponse(account);
    }

    @Override
    public void deleteById(int id) {
        Account account = service.findById(id);
        service.deleteAccount(account);
    }

//    @Override
//    public void exportCsv() throws Exception {
//        service.exportCsv();
//    }
}
