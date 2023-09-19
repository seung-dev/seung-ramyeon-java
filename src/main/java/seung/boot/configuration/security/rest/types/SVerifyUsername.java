package seung.boot.configuration.security.rest.types;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import seung.kimchi.java.types.SType;

@Setter
@Getter
public class SVerifyUsername extends SType {

	private static final long serialVersionUID = 1L;
	
	@Email
	@NotBlank
	private String username;
	
}
