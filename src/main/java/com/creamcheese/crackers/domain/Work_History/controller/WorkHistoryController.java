package com.creamcheese.crackers.domain.Work_History.controller;

import com.creamcheese.crackers.domain.Work_History.dto.WorkHistoryDelReqDto;
import com.creamcheese.crackers.domain.Work_History.dto.WorkHistoryMessageDto;
import com.creamcheese.crackers.domain.Work_History.dto.WorkHistoryReqDto;
import com.creamcheese.crackers.domain.Work_History.dto.WorkHistoryResDto;
import com.creamcheese.crackers.domain.Work_History.service.WorkHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.creamcheese.crackers.global.constant.ResponseConstant.HISTORY_CREATE_SUCCESS;
import static com.creamcheese.crackers.global.constant.ResponseConstant.HISTORY_DELETE_SUCCESS;

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
public class WorkHistoryController {
	private final WorkHistoryService workHistoryService;

	@PostMapping("")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<WorkHistoryMessageDto> create(@RequestBody @Valid final WorkHistoryReqDto requestDto) {
		workHistoryService.create(requestDto);
		return ResponseEntity.ok()
				.body(new WorkHistoryMessageDto(requestDto.getWorkDt() + HISTORY_CREATE_SUCCESS));
	}

	@GetMapping("/{nickname}")
	public List<WorkHistoryResDto> getAllHistory(@PathVariable String nickname) {
		// TODO: @AuthUsers를 통해 현재 로그인한 유저 조회 -> nickname 안 받도록!
		return workHistoryService.getAllHistory(nickname);
	}

	@GetMapping("/{workspaceName}")
	public List<WorkHistoryResDto> getHistoryByWorkspace(@PathVariable String workspaceName) {
		return workHistoryService.getHistoryByWorkspace(workspaceName);
	}

	@DeleteMapping("")
	public ResponseEntity<WorkHistoryMessageDto> delete(@RequestBody @Valid final WorkHistoryDelReqDto requestDto) {
		workHistoryService.delete(requestDto);
		return ResponseEntity.ok()
				.body(new WorkHistoryMessageDto(HISTORY_DELETE_SUCCESS));
	}
}
