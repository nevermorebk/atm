package com.homedirect.support;

@FunctionalInterface
public interface ExecutorNonParamFunction<R> {
	R execute();
}
