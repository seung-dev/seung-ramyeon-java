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
public class SRow extends SType {

	private static final long serialVersionUID = 1L;
	
	private int row_no;
	
	@Builder.Default
	private List<SCell> cells = new ArrayList<>();
	
}
