package seung.kimchi.java.types;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;

import seung.kimchi.java.SJson;

public class SType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String stringify(boolean is_pretty) throws JsonProcessingException {
		return SJson.stringify(this, is_pretty);
	}
	
	public String stringify() throws JsonProcessingException {
		return stringify(false);
	}
	
}
