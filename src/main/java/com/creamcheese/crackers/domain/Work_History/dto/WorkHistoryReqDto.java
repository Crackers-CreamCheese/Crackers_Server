package com.creamcheese.crackers.domain.Work_History.dto;

import com.creamcheese.crackers.domain.Work_History.entity.WorkHistory;
import com.creamcheese.crackers.domain.Workspace.entity.Workspace;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkHistoryReqDto {

	private Integer workspaceId;

	@Valid
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate workDt;

	private Integer startTime;

	private Integer endTime;

	private Integer totalWage;

	@Builder
	public WorkHistoryReqDto(Integer workspaceId, LocalDate workDt,
							 Integer startTime, Integer endTime, Integer totalWage){
		this.workspaceId = workspaceId;
		this.workDt = workDt;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalWage = totalWage;
	}

	public WorkHistory toEntity(Workspace workspace){
		return WorkHistory.builder()
				.workspace(workspace)
				.workDt(this.workDt)
				.startTime(this.startTime)
				.endTime(this.endTime)
				.totalWage(this.totalWage)
				.build();
	}
}
