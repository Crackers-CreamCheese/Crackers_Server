package com.creamcheese.crackers.domain.Work_History.entity;

import com.creamcheese.crackers.domain.Workspace.entity.Workspace;
import com.creamcheese.crackers.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class WorkHistory extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "history_id", updatable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "workspace_id")
	private Workspace workspace;

	@NotNull(message = "근무 날짜를 입력해주세요.")
	private LocalDate workDt;

	@NotNull(message = "시작 시간을 입력해주세요.")
	private Integer startTime;

	@NotNull(message = "종료 시간을 입력해주세요.")
	private Integer endTime;

	@NotNull(message = "오늘의 급여를 입력해주세요.")
	private Integer totalWage;

	@Builder
	public WorkHistory(Workspace workspace, LocalDate workDt, Integer startTime, Integer endTime, Integer totalWage){
		this.workspace = workspace;
		this.workDt = workDt;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalWage = totalWage;
	}
}
