package com.creamcheese.crackers.global.exception.CustomException;

import static com.creamcheese.crackers.global.constant.ResponseConstant.ACCOUNT_NOT_FOUND;
import static com.creamcheese.crackers.global.constant.ResponseConstant.WORKSPACE_NOT_FOUND;

public class WorkspaceNotFoundException extends IllegalArgumentException{
	public WorkspaceNotFoundException() {
		super(WORKSPACE_NOT_FOUND);

	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
