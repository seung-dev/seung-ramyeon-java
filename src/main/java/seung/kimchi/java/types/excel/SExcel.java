package seung.kimchi.java.types.excel;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.kimchi.java.SConvert;
import seung.kimchi.java.types.SType;

@Builder
@Setter
@Getter
public class SExcel extends SType {

	private static final long serialVersionUID = 1L;
	
	private String error_code;
	
	private String error_message;
	
	private int number_of_sheets;
	
	@Builder.Default
	private List<SSheet> sheets = new ArrayList<>();
	
	public void success() {
		success("S000");
	}
	
	public void success(String error_code) {
		this.error_code = error_code;
	}
	
	public void error_code(String error_code) {
		this.error_code = error_code;
	}
	
	public void error_message(String error_message) {
		this.error_message = error_message;
	}
	
	public void exception(Exception exception) {
		error_message(SConvert.throwable(exception));
	}
	
	public void error(String error_code, String error_message) {
		error_code(error_code);
		error_message(error_message);
	}
	
	public boolean hasError() {
		return !"S000".equals(error_code);
	}
	
}
