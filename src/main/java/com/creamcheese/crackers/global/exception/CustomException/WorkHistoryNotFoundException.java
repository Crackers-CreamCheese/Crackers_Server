package com.creamcheese.crackers.global.exception.CustomException;

import static com.creamcheese.crackers.global.constant.ResponseConstant.WORKHISTORY_NOT_FOUND;

public class WorkHistoryNotFoundException extends IllegalArgumentException {
	public WorkHistoryNotFoundException() {
		super(WORKHISTORY_NOT_FOUND);
	}
}
