package com.creamcheese.crackers.exception.CustomException;

import static com.creamcheese.crackers.constant.ResponseConstant.WORKHISTORY_NOT_FOUND;

public class WorkHistoryNotFoundException extends IllegalArgumentException {
	public WorkHistoryNotFoundException() {
		super(WORKHISTORY_NOT_FOUND);
	}
}
