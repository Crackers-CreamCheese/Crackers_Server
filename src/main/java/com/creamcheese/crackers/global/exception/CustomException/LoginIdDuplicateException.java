package com.creamcheese.crackers.global.exception.CustomException;

import static com.creamcheese.crackers.global.constant.ResponseConstant.LOGINID_DUPLICATE;

public class LoginIdDuplicateException extends IllegalArgumentException{
	public LoginIdDuplicateException() {
		super(LOGINID_DUPLICATE);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
