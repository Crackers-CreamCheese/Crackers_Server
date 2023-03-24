package com.creamcheese.crackers.domain.Workspace.entity;


import com.creamcheese.crackers.domain.Account.entity.Account;
import com.creamcheese.crackers.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Workspace extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workspace_id", updatable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Schedule> schedules = new ArrayList<>();


	@NotNull(message = "시급을 입력해주세요")
	private Integer wage = 9160;

	@NotNull(message = "근무지 이름을 입력해주세요.")
	private String name;

	@Builder
	public Workspace(Integer id, Account account, Category category, Integer wage, String name) {
		this.id = id;
		this.account = account;
		this.category = category;
		this.wage = wage;
		this.name = name;
	}

	public void update(List<Schedule> scheduleList, Integer wage, String name) {
		this.schedules.clear();
		for (Schedule schedule : scheduleList) {
			schedules.add(schedule);
		}
		this.wage = wage;
		this.name = name;
	}

	public void addSchedule(Schedule schedule) {
		schedules.add(schedule);
		schedule.setWorkspace(this);
	}


}
