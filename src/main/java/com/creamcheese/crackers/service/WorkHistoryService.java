package com.creamcheese.crackers.service;

import com.creamcheese.crackers.domain.Account.Account;
import com.creamcheese.crackers.domain.Account.AccountRepository;
import com.creamcheese.crackers.dto.workHistory.WorkHistoryDelReqDto;
import com.creamcheese.crackers.dto.workHistory.WorkHistoryReqDto;
import com.creamcheese.crackers.dto.workHistory.WorkHistoryResDto;
import com.creamcheese.crackers.domain.Work_History.WorkHistory;
import com.creamcheese.crackers.domain.Work_History.WorkHistoryRepository;
import com.creamcheese.crackers.domain.Workspace.Workspace;
import com.creamcheese.crackers.domain.Workspace.WorkspaceRepository;
import com.creamcheese.crackers.exception.CustomException.AccountNotFoundException;
import com.creamcheese.crackers.exception.CustomException.WorkHistoryNotFoundException;
import com.creamcheese.crackers.exception.CustomException.WorkspaceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkHistoryService {

	private final AccountRepository accountRepository;
	private final WorkspaceRepository workspaceRepository;
	private final WorkHistoryRepository workHistoryRepository;

	@Transactional
	public void create(WorkHistoryReqDto requestDto) {
		Workspace workspace = workspaceRepository.findById(requestDto.getWorkspaceId())
				.orElseThrow(WorkspaceNotFoundException::new);
		WorkHistory workHistory = requestDto.toEntity(workspace);

		workHistoryRepository.save(workHistory);
	}

	public List<WorkHistoryResDto> getAllHistory(String loginId) {
		Account account = accountRepository.findByLoginId(loginId);
		if(account==null){
			throw new AccountNotFoundException();
		}

		return workspaceRepository.findByAccount(account)
				.stream().filter(Objects::nonNull)
				.map(i -> workHistoryRepository.findByWorkspaceId(i.getId()))
				.map(i -> i.stream().map(WorkHistoryResDto::new).collect(Collectors.toList()))
				.collect(Collectors.toList())

				.stream()
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	public List<WorkHistoryResDto> getHistoryByWorkspace(String workspaceName) {
		Workspace workspace = workspaceRepository.findByName(workspaceName)
				.orElseThrow(WorkspaceNotFoundException::new);

		return workHistoryRepository.findByWorkspaceId(workspace.getId())
				.stream().filter(Objects::nonNull)
				.map(WorkHistoryResDto::new)
				.collect(Collectors.toList());
	}

	@Transactional
	public void delete(WorkHistoryDelReqDto requestDto) {
		WorkHistory workHistory = workHistoryRepository.findById(requestDto.getWorkHistoryId())
				.orElseThrow(WorkHistoryNotFoundException::new);
		workHistoryRepository.delete(workHistory);
	}
}
