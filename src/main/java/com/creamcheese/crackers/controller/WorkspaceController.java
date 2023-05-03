package com.creamcheese.crackers.controller;


import com.creamcheese.crackers.dto.workspace.WorkspaceReqDto;
import com.creamcheese.crackers.dto.workspace.WorkspaceResDto;
import com.creamcheese.crackers.dto.workspace.WorkspaceUpdateReqDto;
import com.creamcheese.crackers.domain.Workspace.Workspace;
import com.creamcheese.crackers.jwt.PrincipalDetails;
import com.creamcheese.crackers.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v2/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
	private final WorkspaceService workspaceService;

	@PostMapping
	public ResponseEntity<WorkspaceResDto> create(
			@RequestBody @Valid final WorkspaceReqDto requestDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		Workspace workspace = workspaceService.create(requestDto, principalDetails.getAccount());
		return ResponseEntity.ok()
				.body(new WorkspaceResDto(workspace));
	}
	@GetMapping
	public ResponseEntity<List<WorkspaceResDto>> search(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		List<WorkspaceResDto> workspaceResDtos = workspaceService.search(principalDetails.getAccount());
		return ResponseEntity.ok()
				.body(workspaceResDtos);
	}

	@PatchMapping("/update")
	public ResponseEntity<WorkspaceResDto> update(
			@RequestBody final WorkspaceUpdateReqDto requestDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails)
	{
		Workspace workspace = workspaceService.update(requestDto, principalDetails.getAccount());
		return ResponseEntity.ok()
				.body(new WorkspaceResDto(workspace));

	}



}
