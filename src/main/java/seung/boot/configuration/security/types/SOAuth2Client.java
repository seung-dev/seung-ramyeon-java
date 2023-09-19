package seung.boot.configuration.security.types;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import seung.kimchi.java.types.SType;

@Builder
@Getter
public class SOAuth2Client extends SType {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private final String client_id;
	
	@NotBlank
	private final String authorization_grant_type;
	
	@NotBlank
	private final String redirect_url;
	
	private final String scope;
	
	@NotBlank
	private final String authorization_url;
	
}
