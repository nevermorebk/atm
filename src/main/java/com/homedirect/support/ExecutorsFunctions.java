package com.homedirect.support;

import com.homedirect.entity.Page;
public interface ExecutorsFunctions<T, R> {

	Page<R> execute(T t);
}
