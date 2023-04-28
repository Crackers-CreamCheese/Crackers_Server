package com.creamcheese.crackers.service;

import antlr.Token;
import com.creamcheese.crackers.dto.account.AccountUpdateReqDto;
import com.creamcheese.crackers.dto.account.SignInReqDto;
import com.creamcheese.crackers.dto.account.WithdrawReqDto;
import com.creamcheese.crackers.domain.Account.Account;
import com.creamcheese.crackers.dto.account.SignUpReqDto;
import com.creamcheese.crackers.domain.Account.AccountRepository;
import com.creamcheese.crackers.dto.token.TokenDTO;
import com.creamcheese.crackers.exception.CustomException.AccountNotFoundException;
import com.creamcheese.crackers.exception.CustomException.LoginIdDuplicateException;
import com.creamcheese.crackers.exception.CustomException.PasswordNotMatchException;
import com.creamcheese.crackers.exception.CustomExcetpion;
import com.creamcheese.crackers.exception.ErrorCode;
import com.creamcheese.crackers.jwt.JwtTokenProvider;
import com.creamcheese.crackers.jwt.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
	@Autowired
	private final AccountRepository accountRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final RedisUtil redisUtil;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public TokenDTO signUp(SignUpReqDto requestDto) {
		if (isExistedLoginId(requestDto.getLoginId())) {
			throw new LoginIdDuplicateException();
		}

		String accessToken = jwtTokenProvider.createAccessToken(requestDto.getLoginId());
		String refreshToken = jwtTokenProvider.createRefreshToken(requestDto.getLoginId());
		TokenDTO tokenDTO = new TokenDTO(accessToken, refreshToken);

		requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
		accountRepository.save(requestDto.toEntity());
		return tokenDTO;
	}

	@Transactional
	public void update(AccountUpdateReqDto requestDto, Account account) {
		account.updateAccount(requestDto.getNickname());
	}

	@Transactional
	public String logout(TokenDTO tokenDTO) {
		if (!jwtTokenProvider.validateToken(tokenDTO.getAccessToken())) {
			throw new CustomExcetpion(ErrorCode.INVALID_ACCESS_TOKEN);
		}
		Authentication authentication = jwtTokenProvider.getAuthentication(tokenDTO.getAccessToken());

		if (redisUtil.getRedisRefreshToken(authentication.getName()) != null) {
			redisUtil.deleteData(authentication.getName());
		}

		Long expiration = jwtTokenProvider.getExpiration(tokenDTO.getAccessToken());
		redisUtil.setRedisLogoutAccTkn(tokenDTO.getAccessToken(), expiration);

		return "success";
	}

	@Transactional
	public void withdraw(Account account, String accessToken) {
		String refreshToken = redisUtil.getRedisRefreshToken(account.getLoginId());
		TokenDTO tokenDTO = new TokenDTO(accessToken, refreshToken);

		logout(tokenDTO);
		account.withdrawAccount();
	}

	@Transactional
	public boolean isExistedLoginId(String loginId) {
		return accountRepository.existsByLoginId(loginId);
	}


	@Transactional
	public void signIn(SignInReqDto requestDto) {
		Account account = accountRepository.findByLoginId(requestDto.getLoginId());
		String encodePw = account.getEncodedPassword();
		if (!passwordEncoder.matches(requestDto.getPassword(), encodePw)) {
			throw new PasswordNotMatchException();
		}
	}

	@Transactional
	public TokenDTO reissueToken(String refreshToken) {
		return jwtTokenProvider.reissueToken(refreshToken);
	}
}