package com.creamcheese.crackers.exception.CustomException;

import static com.creamcheese.crackers.constant.ResponseConstant.USERCARD_ALREADY_EXISTS;

public class UserCardAlreadyExists extends IllegalArgumentException{
	public UserCardAlreadyExists() {
		super(USERCARD_ALREADY_EXISTS);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
