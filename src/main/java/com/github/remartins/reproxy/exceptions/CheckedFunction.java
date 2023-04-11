package com.github.remartins.reproxy.exceptions;

@FunctionalInterface
public interface CheckedFunction<R> {
	R apply() throws Exception;
}




