package com.creamcheese.crackers.domain.Workspace;

import com.creamcheese.crackers.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Schedule extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id", updatable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "workspace_id")
	private Workspace workspace;

	@NotNull(message = "요일을 입력해주세요.")
	private String day;

	@NotNull(message = "시작 시간을 입력해주세요.")
	private Integer startTime;

	@NotNull(message = "종료 시간을 입력해주세요.")
	private Integer endTime;

	@Builder
	public Schedule(Workspace workspace, String day, Integer startTime, Integer endTime) {
		this.workspace = workspace;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}
}
