package com.creamcheese.crackers.dto.account;

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

	@NotNull(message = "비밀번호는 필수입니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!.?,])[A-Za-z\\d!.?,]{2,16}$",
			message = "16자 이내의 영문자 및 숫자와 ?,!,., , 특수문자로 입력해주세요.")
	private String password;

	@Builder
	public WithdrawReqDto(String password) {
		this.password = password;
	}
}
