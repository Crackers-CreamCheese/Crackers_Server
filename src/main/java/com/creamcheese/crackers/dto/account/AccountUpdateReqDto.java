package com.creamcheese.crackers.dto.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountUpdateReqDto {
	@NotNull(message = "닉네임은 필수입니다.")
	private String nickname;


	@Builder
	public AccountUpdateReqDto(String nickname) {
		this.nickname = nickname;
	}
}
