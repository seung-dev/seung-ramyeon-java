package seung.boot.configuration.web.rest.types;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import seung.kimchi.java.types.SType;

@Setter
@Getter
public class SRestHmac extends SType {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String algorithm;
	
	@NotBlank
	private String url_path;
	
	@NotBlank
	private String access_key;
	
	@NotBlank
	private String secret_key;
	
}
