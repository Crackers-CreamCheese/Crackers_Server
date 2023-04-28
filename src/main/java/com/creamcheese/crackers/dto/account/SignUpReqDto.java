package com.creamcheese.crackers.dto.account;

import com.creamcheese.crackers.domain.Account.Account;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpReqDto {

	@NotBlank
	private String loginId;

	@NotNull(message = "비밀번호는 필수입니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!.?,])[A-Za-z\\d!.?,]{2,16}$",
			message = "16자 이내의 영문자 및 숫자와 ?,!,., , 특수문자로 입력해주세요.")
	private String password;

	@NotBlank(message = "닉네임은 필수입니다. ")
	private String nickname;

	@Builder
	public SignUpReqDto( String loginId, String password, String nickname) {
		this.loginId = loginId;
		this.password = password;
		this.nickname = nickname;
	}

	public Account toEntity() {
		return Account.builder()
				.encodedPassword(this.password)
				.loginId(this.loginId)
				.nickname(this.nickname)
				.build();
	}
}
