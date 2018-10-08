package com.homdirect.application.controller;

import com.homdirect.application.constant.ErrorCode;
import com.homdirect.application.exception.ATMException;
import com.homdirect.application.response.ATMResponse;
import com.homdirect.application.support.ExecutorFunction;
import com.homdirect.application.support.ExecutorFunctionPage;
import com.homdirect.application.support.ExecutorSupplier;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

public abstract class AbstractController<P> {

    private final static Logger logger = Logger.getLogger(AccountController.class);

    protected @Autowired
    P processor;

    protected <T, R> ATMResponse<R> apply(T t, ExecutorFunction<T, R> executor) {
        try {
            R data = executor.execute(t);
            return new ATMResponse<>(ErrorCode.SUCCESS, ErrorCode.SUCCESS_MES, data);
        } catch (ATMException e) {
            logger.error(e.getMessage());
            return new ATMResponse<>(e.getCode(), e.getMessage(), null);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e.getCause());
            return new ATMResponse<>(ErrorCode.UNKNOWN, e.getMessage(), null);
        }
    }

    protected <T, R> ATMResponse<Page<R>> apply(T t, ExecutorFunctionPage<T, R> executor) {
        try {
            Page<R> data = executor.execute(t);
            return new ATMResponse<>(ErrorCode.SUCCESS, ErrorCode.SUCCESS_MES, data);
        } catch (ATMException e) {
            logger.error(e.getMessage());
            return new ATMResponse<>(e.getCode(), e.getMessage(), null);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e.getCause());
            return new ATMResponse<>(ErrorCode.UNKNOWN, e.getMessage(), null);
        }
    }

    protected <R> ATMResponse<R> apply(ExecutorSupplier<R> executor) {
        try {
            R data = executor.execute();
            return new ATMResponse<>(ErrorCode.SUCCESS, ErrorCode.SUCCESS_MES, data);
        } catch (ATMException e) {
            logger.error(e.getMessage());
            return new ATMResponse<>(e.getCode(), e.getMessage(), null);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e.getCause());
            return new ATMResponse<>(ErrorCode.UNKNOWN, e.getMessage(), null);
        }
    }
}
