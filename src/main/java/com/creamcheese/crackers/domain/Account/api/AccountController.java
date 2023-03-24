package com.creamcheese.crackers.domain.Account.api;

import com.creamcheese.crackers.domain.Account.dto.*;
import com.creamcheese.crackers.domain.Account.entity.Account;
import com.creamcheese.crackers.domain.Account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.creamcheese.crackers.global.constant.ResponseConstant.WITHDRAW_SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
	private final AccountService accountService;

	@PostMapping("/signup")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<AccountResDto> signUp(@RequestBody @Valid final SignUpReqDto requestDto) {
		Integer id = accountService.signUp(requestDto);
		Account findAccount = accountService.findById(id);
		return ResponseEntity.ok()
				.body(new AccountResDto(findAccount));
	}

	@PatchMapping("/profile")
	public ResponseEntity<AccountResDto> update(@RequestBody @Valid final AccountUpdateReqDto requestDto) {
		//TODO: 토큰을 통해 로그인된 사람의 정보를 가져와 id로 반환, @AuthUser
		Integer id = accountService.update(requestDto);
		Account findAccount = accountService.findById(id);
		return ResponseEntity.ok()
				.body(new AccountResDto(findAccount));
	}

	@PatchMapping("/withdraw")
	public ResponseEntity<String> withdraw(@RequestBody @Valid final WithdrawReqDto requestDto)
	{//TODO:토큰을 통해 로그인된 사람의 정보를 가져와 id로 반환, @AuthUser 필요
		accountService.withdraw(requestDto);
		return ResponseEntity.ok()
				.body(WITHDRAW_SUCCESS);

	}

	@PostMapping("/signin")
	public ResponseEntity<AccountResDto> signIn(@RequestBody @Valid final SignInReqDto requestDto)
	{
		Account findAccount = accountService.signIn(requestDto);
		return ResponseEntity.ok()
				.body(new AccountResDto(findAccount));
	}


}
