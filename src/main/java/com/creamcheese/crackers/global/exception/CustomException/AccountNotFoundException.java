package com.creamcheese.crackers.global.exception.CustomException;


import static com.creamcheese.crackers.global.constant.ResponseConstant.ACCOUNT_NOT_FOUND;

public class AccountNotFoundException extends RuntimeException{
	public AccountNotFoundException() {
		super(ACCOUNT_NOT_FOUND);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
