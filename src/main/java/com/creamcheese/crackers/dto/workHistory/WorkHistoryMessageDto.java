package com.creamcheese.crackers.dto.workHistory;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkHistoryMessageDto {
	private String message;

	@Builder
	public WorkHistoryMessageDto(String message){
		this.message = message;
	}
}
