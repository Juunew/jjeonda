package com.fintech.jjeondaproject.entity.user;

import com.fintech.jjeondaproject.dto.user.UserReqDto;
import com.fintech.jjeondaproject.entity.BaseTime;
import com.fintech.jjeondaproject.util.encrypt.Encryption;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@Getter
@Entity
public class UserEntity extends BaseTime {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // identity, sequence, table, auto
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "account_id")
	private String accountId;
	
	private String password;
	
	private String name;
	
	private String phone;
	
	private String gender;
	
	private String birth;
	
	private String email;

	@Column(name = "agreement_yn")
	private String agreementYn;

	private UserEntity(String accountId, String password, String name, String phone, String gender, String birth, String email, String agreementYn) {
		this.accountId = accountId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.birth = birth;
		this.email = email;
		this.agreementYn = agreementYn;
	}

	public static UserEntity of(UserReqDto reqDto) {
		return new UserEntity(
				reqDto.getAccountId(),
				Encryption.encryptSHA512(reqDto.getPassword()),
				reqDto.getName(),
				reqDto.getPhone(),
				reqDto.getGender(),
				reqDto.getBirth(),
				reqDto.getEmail(),
				reqDto.getAgreementYn()
		);
	}
}
