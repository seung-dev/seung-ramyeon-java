package seung.boot.configuration.web.types;

import lombok.Getter;
import lombok.Setter;
import seung.kimchi.java.types.SType;

@Setter
@Getter
public class SRequestBody extends SType {

	private static final long serialVersionUID = 1L;
	
	private Object data;
	
}
