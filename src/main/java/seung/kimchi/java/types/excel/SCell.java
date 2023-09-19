package seung.kimchi.java.types.excel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.kimchi.java.types.SType;

@Builder
@Setter
@Getter
public class SCell extends SType {

	private static final long serialVersionUID = 1L;
	
	private int row_no;
	
	private int column_no;
	
	private String cell_text;
	
}
