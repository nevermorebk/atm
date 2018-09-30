package com.homedirect.controller;

import com.homedirect.support.ExecutorNonParamFunction;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.homedirect.constant.ErrorCode;
import com.homedirect.entity.Page;
import com.homedirect.exception.ATMException;
import com.homedirect.response.ATMResponse;
import com.homedirect.support.ExecutorFunction;
import com.homedirect.support.ExecutorsFunctions;

// thêm log cho Exception
// sửa lại hàm ATMResponse
public abstract class AbstractController<P> {

	final Logger logger = Logger.getLogger(AbstractController.class);
	protected @Autowired P processor;

	protected <T, R> ATMResponse<R> apply(T t, ExecutorFunction<T, R> executor) {
		try {
			R data = executor.execute(t);
			return new ATMResponse<>(ErrorCode.SUCCESS, ErrorCode.SUCCESS_MES, data);
		} catch (ATMException e) {
			return new ATMResponse<>(e.getCode(), e.getMessage(), null);
		} catch (Throwable e) {
			return new ATMResponse<>(ErrorCode.UNKNOWN, e.getMessage(), null);
		}
	}
	
	protected <R> ATMResponse<R> apply(ExecutorNonParamFunction<R> executor) {
		try {
			R data = executor.execute();
			return new ATMResponse<>(ErrorCode.SUCCESS, ErrorCode.SUCCESS_MES, data);
		} catch (ATMException e) {
			return new ATMResponse<>(e.getCode(), e.getMessage(), null);
		} catch (Throwable e) {
			return new ATMResponse<>(ErrorCode.UNKNOWN, e.getMessage(), null);
		}
	}
	
	protected <T, R> ATMResponse<Page<R>> apply(T t, ExecutorsFunctions<T, R> executor) {
		try {
			Page<R> data = executor.execute(t);
			return new ATMResponse<>(ErrorCode.SUCCESS, ErrorCode.SUCCESS_MES, data);
		} catch (ATMException e) {
			return new ATMResponse<>(e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			return new ATMResponse<>(ErrorCode.UNKNOWN, e.getMessage(), null);
		}
	}
}
