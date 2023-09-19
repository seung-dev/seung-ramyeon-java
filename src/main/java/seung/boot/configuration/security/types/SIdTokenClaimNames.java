package seung.boot.configuration.security.types;

import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

public interface SIdTokenClaimNames extends IdTokenClaimNames {

	public static final String _S_CLAIM_SUB = "sub";
	
	public static final String _S_CLAIM_ID = "id";
	
	public static final String _S_CLAIM_RESPONSE = "response";
	
}
