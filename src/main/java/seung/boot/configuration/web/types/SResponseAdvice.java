package seung.boot.configuration.web.types;

import lombok.Builder;
import lombok.Getter;
import seung.kimchi.java.types.SType;

@Builder
@Getter
public class SResponseAdvice extends SType {

	private static final long serialVersionUID = 1L;
	
	private final long timestamp;
	
	private final int status;
	
	private final String error;
	
	private final String message;
	
	private final String path;
	
	private final String filter;
	
}
