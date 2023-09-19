package seung.kimchi.java.types.excel;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.kimchi.java.types.SType;

@Builder
@Setter
@Getter
public class SSheet extends SType {

	private static final long serialVersionUID = 1L;
	
	private String sheet_name;
	
	private int physical_number_of_rows;
	
	@Builder.Default
	private List<SRow> rows = new ArrayList<>();
	
}
