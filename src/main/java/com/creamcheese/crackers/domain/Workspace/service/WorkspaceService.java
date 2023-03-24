package com.creamcheese.crackers.domain.Workspace.service;

import com.creamcheese.crackers.domain.Account.entity.Account;
import com.creamcheese.crackers.domain.Account.service.AccountService;
import com.creamcheese.crackers.domain.Workspace.dto.ScheduleDto;
import com.creamcheese.crackers.domain.Workspace.dto.WorkspaceReqDto;
import com.creamcheese.crackers.domain.Workspace.dto.WorkspaceResDto;
import com.creamcheese.crackers.domain.Workspace.dto.WorkspaceUpdateReqDto;
import com.creamcheese.crackers.domain.Workspace.entity.Category;
import com.creamcheese.crackers.domain.Workspace.entity.Schedule;
import com.creamcheese.crackers.domain.Workspace.entity.Workspace;
import com.creamcheese.crackers.domain.Workspace.repository.CategoryRepository;
import com.creamcheese.crackers.domain.Workspace.repository.ScheduleRepository;
import com.creamcheese.crackers.domain.Workspace.repository.WorkspaceRepository;
import com.creamcheese.crackers.global.exception.CustomException.AccountNotFoundException;
import com.creamcheese.crackers.global.exception.CustomException.CategoryNotFoundException;
import com.creamcheese.crackers.global.exception.CustomException.WorkspaceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WorkspaceService {
	private final WorkspaceRepository workspaceRepository;
	private final CategoryRepository categoryRepository;
	private final ScheduleRepository scheduleRepository;
	private final AccountService accountService;


	public Workspace create(WorkspaceReqDto requestDto) {
		Account account = accountService.findByLoginId(requestDto.getLoginId());
		Category category = findByCategoryId(requestDto.getCategoryId());
		Workspace workspace = workspaceRepository.save(requestDto.toEntity(account, category));

		List<Schedule> scheduleList = scheduleRepository.saveAll(requestDto.getScheduleList().stream().filter(Objects::nonNull)
				.map(scheduleReqDto -> scheduleReqDto.toEntity(workspace)).collect(Collectors.toList()));
		for (Schedule schedule : scheduleList) {
			workspace.addSchedule(schedule);
		}
		return workspace;
	}

	public List<WorkspaceResDto> search(String loginId) {
		Account account = accountService.findByLoginId(loginId);
		List<Workspace> workspaces = findWorkspaceByAccount(account);
		List<WorkspaceResDto> workspaceResDtos = workspaces.stream()
				.map(i -> new WorkspaceResDto(i)).collect(Collectors.toList());
		return workspaceResDtos;
	}


	public Workspace update(WorkspaceUpdateReqDto requestDto) {
		Account account = accountService.findByLoginId(requestDto.getLoginId());
		Workspace workspace = findByWorkspaceId(requestDto.getWorkspaceId());
		List<Schedule> scheduleList = scheduleRepository.saveAll(requestDto.getScheduleList().stream().filter(Objects::nonNull).map(scheduleReqDto -> scheduleReqDto.toEntity(workspace)).collect(Collectors.toList()));
		workspace.update(scheduleList, requestDto.getWage(), requestDto.getName());
		return workspace;

	}

	public Category findByCategoryId(Integer id) {
		return categoryRepository.findById(id)
				.orElseThrow(CategoryNotFoundException::new);
	}

	public Workspace findByWorkspaceId(Integer id) {
		return workspaceRepository.findById(id)
				.orElseThrow(WorkspaceNotFoundException::new);
	}

	public List<Workspace> findWorkspaceByAccount(Account account) {
		List<Workspace> workspaces = workspaceRepository.findByAccount(account);
		if (workspaces == null) {
			throw new WorkspaceNotFoundException();
		}
		return workspaces;

	}


}
