package com.creamcheese.crackers.domain.Account;

import com.creamcheese.crackers.domain.Workspace.Workspace;
import com.creamcheese.crackers.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.creamcheese.crackers.domain.Account.AccountStatus.UNREGISTERED;

@Entity
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id", updatable = false)
	private Integer accountId;

	@NotNull(message = "아이디는 필수입니다.")
	private String loginId;

	@NotNull(message = "닉네임은 필수입니다. ")
	private String nickname;

	@NotNull(message = "비밀번호는 필수입니다.")
	private String encodedPassword;

	@OneToMany(mappedBy = "account")
	private List<Workspace>  workspaces = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	@ColumnDefault("'REGISTERED'")
	private AccountStatus status;

	@Builder
	public Account(String loginId, String nickname, String encodedPassword) {
		this.loginId = loginId;
		this.nickname = nickname;
		this.encodedPassword = encodedPassword;
	}

	public void updateAccount(String nickname){
		this.nickname = nickname;
	}

	public void withdrawAccount(){
		this.status = UNREGISTERED;
	}


}
