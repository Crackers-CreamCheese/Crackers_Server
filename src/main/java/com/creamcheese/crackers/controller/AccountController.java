package com.creamcheese.crackers.controller;

import com.creamcheese.crackers.domain.Account.Account;
import com.creamcheese.crackers.dto.token.RefreshTokenDTO;
import com.creamcheese.crackers.dto.token.TokenDTO;
import com.creamcheese.crackers.jwt.PrincipalDetails;
import com.creamcheese.crackers.service.AccountService;
import com.creamcheese.crackers.dto.account.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.util.Optional;

import static com.creamcheese.crackers.constant.ResponseConstant.WITHDRAW_SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
	private final AccountService accountService;

	public AccountController(AccountService accountService){
		this.accountService=accountService;
	}

	@PostMapping("/signup")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<TokenDTO> signUp(@RequestBody @Valid final SignUpReqDto requestDto) {
		TokenDTO tokenDTO = accountService.signUp(requestDto);
		return ResponseEntity.ok()
				.body(tokenDTO);
	}

	@PatchMapping("/profile")
	public ResponseEntity<AccountResDto> update(
			@RequestBody @Valid final AccountUpdateReqDto requestDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		Account account = principalDetails.getAccount();
		accountService.update(requestDto, account);
		return ResponseEntity.ok()
				.body(new AccountResDto(account));
	}

	@PatchMapping("/withdraw")
	public ResponseEntity<String> withdraw(
			@RequestHeader(value = "Authorization") String header,
			@AuthenticationPrincipal PrincipalDetails principalDetails)
	{
		Account account = principalDetails.getAccount();
		String accessToken = header.substring(7);
		accountService.withdraw(account, accessToken);
		return ResponseEntity.ok()
				.body(WITHDRAW_SUCCESS);
	}

	@PostMapping("/signin")
	public ResponseEntity<TokenDTO> signIn(@RequestBody @Valid final SignInReqDto requestDto)
	{
		accountService.signIn(requestDto);
		return ResponseEntity.ok()
				.body(new TokenDTO());
	}

	@PostMapping("/token/refresh")
	public ResponseEntity<TokenDTO> refresh(@RequestBody @Valid RefreshTokenDTO reqDTO){
		TokenDTO resDTO = accountService.reissueToken(reqDTO.getRefreshToken());
		return ResponseEntity.ok(resDTO);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestBody @Valid TokenDTO tokenDTO){
		return ResponseEntity.ok(accountService.logout(tokenDTO));
	}


}
