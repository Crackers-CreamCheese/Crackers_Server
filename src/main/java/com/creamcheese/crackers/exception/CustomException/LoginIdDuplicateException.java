package com.creamcheese.crackers.exception.CustomException;

import static com.creamcheese.crackers.constant.ResponseConstant.LOGINID_DUPLICATE;

public class LoginIdDuplicateException extends IllegalArgumentException{
	public LoginIdDuplicateException() {
		super(LOGINID_DUPLICATE);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
