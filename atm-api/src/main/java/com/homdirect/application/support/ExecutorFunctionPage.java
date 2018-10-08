package com.homdirect.application.support;

import org.springframework.data.domain.Page;

@FunctionalInterface
public interface ExecutorFunctionPage<T, R> {
    Page<R> execute(T t);
}
