package com.creamcheese.crackers.dto.workHistory;

import com.creamcheese.crackers.domain.Work_History.WorkHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class WorkHistoryResDto {

	private Integer workHistoryId;

	private Integer workspaceId;

	private String workspaceName;

	private LocalDate workDt;

	private Integer startTime;

	private Integer endTime;

	private Integer totalWage;

	@Builder
	public WorkHistoryResDto(WorkHistory workHistory){
		this.workHistoryId = workHistory.getHistoryId();
		this.workspaceId = workHistory.getWorkspace().getId();
		this.workspaceName = workHistory.getWorkspace().getName();
		this.workDt = workHistory.getWorkDt();
		this.startTime = workHistory.getStartTime();
		this.endTime = workHistory.getEndTime();
		this.totalWage = workHistory.getTotalWage();
	}
}
