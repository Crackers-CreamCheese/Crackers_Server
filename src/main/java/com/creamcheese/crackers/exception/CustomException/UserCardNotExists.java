package com.creamcheese.crackers.exception.CustomException;

import static com.creamcheese.crackers.constant.ResponseConstant.USERCARD_NOT_EXISTS;

public class UserCardNotExists extends IllegalArgumentException {
	public UserCardNotExists() {
		super(USERCARD_NOT_EXISTS);
	}
}
