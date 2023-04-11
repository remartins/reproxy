package com.github.remartins.reproxy.exceptions;

public class CatchException {

	public static <R> R catchException(CheckedFunction<R> checkedFunction) {
		try {
			return checkedFunction.apply();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
