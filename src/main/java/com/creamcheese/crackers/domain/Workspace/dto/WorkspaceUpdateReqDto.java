package com.creamcheese.crackers.domain.Workspace.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkspaceUpdateReqDto {

	@NotBlank
	private Integer workspaceId;

	@NotBlank(message = "아이디를 필수로 입력해주세요.")
	private String loginId;

	@NotBlank(message = "근무지 이름을 입력해주세요.")
	private String name;

	@NotBlank(message = "시급을 입력해주세요")
	private Integer wage;

	private List<ScheduleDto> scheduleList;

	private Integer categoryId;

}
