package com.creamcheese.crackers.domain.Account.dto;

import com.creamcheese.crackers.domain.Account.entity.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountUpdateReqDto {
	@NotNull(message = "아이디는 필수입니다.")
	private String loginId;
	@NotNull(message = "닉네임은 필수입니다.")
	private String nickname;


	@Builder
	public AccountUpdateReqDto(String loginId, String nickname) {
		this.loginId = loginId;
		this.nickname = nickname;
	}
}
