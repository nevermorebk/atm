package com.homdirect.application.support;

@FunctionalInterface
public interface ExecutorSupplier<R> {
    R execute();
}
