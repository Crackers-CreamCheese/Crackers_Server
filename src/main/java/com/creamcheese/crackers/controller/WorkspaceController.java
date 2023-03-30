package com.creamcheese.crackers.controller;


import com.creamcheese.crackers.dto.workspace.WorkspaceReqDto;
import com.creamcheese.crackers.dto.workspace.WorkspaceResDto;
import com.creamcheese.crackers.dto.workspace.WorkspaceUpdateReqDto;
import com.creamcheese.crackers.domain.Workspace.Workspace;
import com.creamcheese.crackers.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
	private final WorkspaceService workspaceService;

	@PostMapping
	public ResponseEntity<WorkspaceResDto> create(@RequestBody @Valid final WorkspaceReqDto requestDto) {
		Workspace workspace = workspaceService.create(requestDto);
		return ResponseEntity.ok()
				.body(new WorkspaceResDto(workspace));
	}
	@GetMapping
	public ResponseEntity<List<WorkspaceResDto>> search(@RequestParam String loginId) {
		List<WorkspaceResDto> workspaceResDtos = workspaceService.search(loginId);
		return ResponseEntity.ok()
				.body(workspaceResDtos);
	}

	@PatchMapping("/update")
	public ResponseEntity<WorkspaceResDto> update(@RequestBody final WorkspaceUpdateReqDto requestDto)
	{
		Workspace workspace = workspaceService.update(requestDto);
		return ResponseEntity.ok()
				.body(new WorkspaceResDto(workspace));

	}



}
