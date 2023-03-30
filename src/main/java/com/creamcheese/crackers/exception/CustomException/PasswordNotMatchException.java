package com.creamcheese.crackers.exception.CustomException;


import static com.creamcheese.crackers.constant.ResponseConstant.PASSWORD_NOT_MATCH;

public class PasswordNotMatchException extends IllegalArgumentException {
	public PasswordNotMatchException() {
		super(PASSWORD_NOT_MATCH);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
