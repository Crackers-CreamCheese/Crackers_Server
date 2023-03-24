package com.creamcheese.crackers.domain.Workspace.dto;

import com.creamcheese.crackers.domain.Workspace.entity.Schedule;
import com.creamcheese.crackers.domain.Workspace.entity.Workspace;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleDto {
	@NotNull(message = "요일을 입력해주세요.")
	private String day;

	@NotNull(message = "시작 시간을 입력해주세요.")
	private Integer startTime;

	@NotNull(message = "종료 시간을 입력해주세요.")
	private Integer endTime;

	@Builder
	public ScheduleDto(String day, Integer startTime, Integer endTime) {
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}


	public Schedule toEntity(Workspace workspace){
		return Schedule.builder()
				.workspace(workspace)
				.day(this.day)
				.startTime(this.startTime)
				.endTime(this.endTime)
				.build();

	}

	public static ScheduleDto of(Schedule schedule){
		return ScheduleDto.builder()
				.day(schedule.getDay())
				.startTime(schedule.getStartTime())
				.endTime(schedule.getEndTime())
				.build();
	}



}
