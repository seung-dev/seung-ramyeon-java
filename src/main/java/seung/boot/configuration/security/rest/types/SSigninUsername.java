package seung.boot.configuration.security.rest.types;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import seung.kimchi.java.types.SType;

@Setter
@Getter
public class SSigninUsername extends SType {

	private static final long serialVersionUID = 1L;
	
	@Email
	@NotBlank
	private String username;
	
//	@Pattern(regexp = "^(?!.* )(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,16}$", message = "비밀번호는 8 ~ 16자 영문, 숫자, 특수문자를 사용하세요.")
	@NotBlank
	private String secret;
	
}
