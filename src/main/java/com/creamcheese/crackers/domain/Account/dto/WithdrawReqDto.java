package com.creamcheese.crackers.domain.Account.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WithdrawReqDto {
	@NotNull(message = "아이디는 필수입니다.")
	private String loginId;

	@NotNull(message = "비밀번호는 필수입니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!.?,])[A-Za-z\\d!.?,]{2,16}$",
			message = "16자 이내의 영문자 및 숫자와 ?,!,., , 특수문자로 입력해주세요.")
	private String password;

	@Builder
	public WithdrawReqDto(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}
}
