package com.creamcheese.crackers.exception.CustomException;

import static com.creamcheese.crackers.constant.ResponseConstant.WORKSPACE_NOT_FOUND;

public class WorkspaceNotFoundException extends IllegalArgumentException{
	public WorkspaceNotFoundException() {
		super(WORKSPACE_NOT_FOUND);

	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
