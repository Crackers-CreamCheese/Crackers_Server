package com.creamcheese.crackers.domain.Workspace.dto;

import com.creamcheese.crackers.domain.Account.entity.Account;
import com.creamcheese.crackers.domain.Workspace.entity.Category;
import com.creamcheese.crackers.domain.Workspace.entity.Schedule;
import com.creamcheese.crackers.domain.Workspace.entity.Workspace;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkspaceReqDto {
	@NotBlank(message = "아이디를 필수로 입력해주세요.")
	private String loginId;

	@NotNull(message = "근무지 이름을 입력해주세요.")
	private String name;

	private Integer wage;

	private List<ScheduleDto> scheduleList;

	private Integer categoryId;

	@Builder
	public WorkspaceReqDto(String loginId, String name, Integer wage, Integer categoryId) {
		this.loginId = loginId;
		this.name = name;
		this.wage = wage;
		this.categoryId = categoryId;
	}

	public Workspace toEntity(Account account, Category category){
		return Workspace.builder()
				.name(this.name)
				.wage(this.wage)
				.account(account)
				.category(category)
				.build();
	}



}
