package com.creamcheese.crackers.dto.account;

import com.creamcheese.crackers.domain.Account.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountResDto {
	private String loginId;
	private String nickname;

	@Builder
	public AccountResDto(Account account) {
		this.loginId = account.getLoginId();
		this.nickname = account.getNickname();
	}
}
