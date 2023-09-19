package seung.kimchi.java.types;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SSignPriKey extends SType {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private byte[] encoded;
	
	private String private_key_algorythm_oid;
	
	private String encryption_algorithm_oid;
	
	private byte[] salt;
	
	private int iteration_count;
	
	private int key_length;
	
	private String prf_algorithm_oid;
	
	private byte[] iv;
	
	private byte[] private_key;
	
}
