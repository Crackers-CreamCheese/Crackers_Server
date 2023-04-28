package com.creamcheese.crackers.exception.CustomException;

import static com.creamcheese.crackers.constant.ResponseConstant.USERCARD_NOT_FOUND;

public class UserCardNotFoundException extends IllegalArgumentException {
	public UserCardNotFoundException() {
		super(USERCARD_NOT_FOUND);
	}
}
