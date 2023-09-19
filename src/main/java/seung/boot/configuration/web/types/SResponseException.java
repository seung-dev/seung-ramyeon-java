package seung.boot.configuration.web.types;

public class SResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final SResponseBody response_body;
	
	private final Exception exception;
	
	public SResponseException(Exception exception, SResponseBody response_body) {
		this.exception = exception;
		this.response_body = response_body;
	}
	
	public SResponseBody response_body() {
		return this.response_body;
	}
	
	public Exception exception() {
		return this.exception;
	}
	
	public String error_code() {
		return this.response_body.getError_code();
	}
	
	public SError s_error() {
		return SError.resolve(this.response_body.getError_code());
	}
	
	public String error_message() {
		return this.response_body.getError_message();
	}
	
}
