package com.kaffa.util;

import java.util.List;

public class Util {

	public static boolean empty(String value) {
		return value == null || value.isEmpty();
	}

	public static boolean notEmpty(String value) {
		return value != null && !value.isEmpty();
	}

	public static boolean empty(List<?> value) {
		return value == null || value.isEmpty();
	}

	public static boolean notEmpty(List<?> value) {
		return value != null && !value.isEmpty();
	}

}
