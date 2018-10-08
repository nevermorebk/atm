package com.homdirect.application.support;

@FunctionalInterface
public interface ExecutorFunction<T, R> {
    R execute(T t);
}
