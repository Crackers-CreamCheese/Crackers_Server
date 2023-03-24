package com.creamcheese.crackers.domain.Account.service;

import com.creamcheese.crackers.domain.Account.dto.AccountUpdateReqDto;
import com.creamcheese.crackers.domain.Account.dto.SignInReqDto;
import com.creamcheese.crackers.domain.Account.dto.WithdrawReqDto;
import com.creamcheese.crackers.domain.Account.entity.Account;
import com.creamcheese.crackers.domain.Account.dto.SignUpReqDto;
import com.creamcheese.crackers.domain.Account.repository.AccountRepository;
import com.creamcheese.crackers.global.exception.CustomException.AccountNotFoundException;
import com.creamcheese.crackers.global.exception.CustomException.LoginIdDuplicateException;
import com.creamcheese.crackers.global.exception.CustomException.PasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
	private final AccountRepository accountRepository;

	@Transactional
	public Integer signUp(SignUpReqDto requestDto) {
		if (isExistedLoginId(requestDto.getLoginId())) {
			throw new LoginIdDuplicateException();
		}
		//TODO: 비밀번호 암호화 적용
		Account account = accountRepository.save(requestDto.toEntity());
		return account.getAccountId();
	}

	@Transactional
	public Integer update(AccountUpdateReqDto requestDto) {
		Account account = findByLoginId(requestDto.getLoginId());
		account.updateAccount(requestDto.getNickname());
		return account.getAccountId();
	}

	@Transactional
	public void withdraw(WithdrawReqDto requestDto) {
		Account account = findByLoginId(requestDto.getLoginId());
		if (!checkPassword(account, requestDto.getPassword())) {
			throw new PasswordNotMatchException();
		}
		account.withdrawAccount();
	}

	@Transactional
	public boolean isExistedLoginId(String loginId) {
		return accountRepository.existsByLoginId(loginId);
	}

	/*----------Auth관련 -----*/

	@Transactional
	public Account signIn(SignInReqDto requestDto) {
		Account account = findByLoginId(requestDto.getLoginId());
		if (!checkPassword(account, requestDto.getPassword())) {
			throw new PasswordNotMatchException();
		}
		return account;
	}

	/* repository 관련 */
	@Transactional //TODO:readOnly 적
	public Account findById(Integer id) {
		return accountRepository.findById(id)
				.orElseThrow(AccountNotFoundException::new);
	}

	@Transactional
	public Account findByLoginId(String loginId) {
		return accountRepository.findByLoginId(loginId)
				.orElseThrow(AccountNotFoundException::new);
	}

	@Transactional
	public boolean checkPassword(Account account, String password) {
		if (account.getEncodedPassword().equals(password)) {
			return true;
		} else {
			return false;
		}

	}


}
