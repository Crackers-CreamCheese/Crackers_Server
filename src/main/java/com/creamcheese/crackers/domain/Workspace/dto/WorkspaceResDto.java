package com.creamcheese.crackers.domain.Workspace.dto;

import com.creamcheese.crackers.domain.Workspace.entity.Schedule;
import com.creamcheese.crackers.domain.Workspace.entity.Workspace;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkspaceResDto {
	private Integer workspaceId;
	private String name;

	private Integer wage;

	private List<ScheduleDto> schedules;

	private Integer categoryId;

	@Builder
	public WorkspaceResDto(Workspace workspace) {
		this.workspaceId = workspace.getId();
		this.name = workspace.getName();
		this.wage = workspace.getWage();
		this.schedules = workspace.getSchedules().stream().map(ScheduleDto::of).collect(Collectors.toList());
		this.categoryId = workspace.getCategory().getId();
	}
}
