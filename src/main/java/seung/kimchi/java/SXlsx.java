package seung.kimchi.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import seung.kimchi.java.types.SLinkedHashMap;
import seung.kimchi.java.types.excel.SCell;
import seung.kimchi.java.types.excel.SExcel;
import seung.kimchi.java.types.excel.SRow;
import seung.kimchi.java.types.excel.SSheet;

public class SXlsx {

	public static SExcel read(byte[] file) {
		
		SExcel sExcel = SExcel.builder()
				.error_code("E999")
				.build()
				;
		
		XSSFWorkbook workbook = null;
		
		try {
			
			workbook = new XSSFWorkbook(new ByteArrayInputStream(file));
			sExcel.setNumber_of_sheets(workbook.getNumberOfSheets());
			
			for(Sheet sheet : workbook) {
				
				sExcel.getSheets().add(read_sheet(sheet));
				
			}// end of sheet
			
			sExcel.success();
			
		} catch (IOException e) {
			sExcel.exception(e);
			log.error("exception=", e);
		}// end of try
		
		return sExcel;
	}// end of read
	
	private static SSheet read_sheet(Sheet sheet) {
		SSheet sSheet = SSheet.builder()
				.sheet_name(sheet.getSheetName())
				.physical_number_of_rows(sheet.getPhysicalNumberOfRows())
				.build()
				;
		for(Row row : sheet) {
			sSheet.getRows().add(read_row(row));
		}// end of row
		return sSheet;
	}// end of read_sheet
	
	private static SRow read_row(Row row) {
		SRow sRow = SRow.builder()
				.row_no(row.getRowNum())
				.build()
				;
		for(Cell cell : row) {
			sRow.getCells().add(read_cell(cell));
		}// end of cell
		return sRow;
	}// end of read_row
	
	private static SCell read_cell(Cell cell) {
		SCell sCell = SCell.builder()
				.row_no(cell.getRowIndex())
				.column_no(cell.getColumnIndex())
				.build();
		sCell.setCell_text(cell_value(cell));
		return sCell;
	}// end of read_cell
	
//	private static String[][] read(Sheet sheet, int row_no_begin, int row_no_max, int cell_no_max) {
//		
//		String[][] data = null;
//		
//		try {
//			
//			Row row = null;
//			Cell cell = null;
//			
//			int getPhysicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//			if(row_no_max > getPhysicalNumberOfRows + 1) {
//				row_no_max = getPhysicalNumberOfRows + 1;
//			}
//			
//			data = new String[getPhysicalNumberOfRows][cell_no_max];
//			for(int row_no = row_no_begin; row_no <= row_no_max; row_no++) {
//				row = sheet.getRow(row_no);
//				for(int cell_no = 0; cell_no <= cell_no_max; cell_no++) {
//					cell = row.getCell(cell_no);
//					data[row_no][cell_no] = cell_value(cell);
//				}
//			}
//			
//		} catch (Exception e) {
//			log.error("exception=", e);
//		}
//		
//		return data;
//	}
	
	public static byte[] write(
			String request_code
			, byte[] read_bytes
			, SLinkedHashMap excel_data
			) {
		
		byte[] excel = null;
		
		ByteArrayInputStream byteArrayInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		XSSFWorkbook read_workbook = null;
		try {
			
			byteArrayInputStream = new ByteArrayInputStream(read_bytes);
			byteArrayOutputStream = new ByteArrayOutputStream();
			read_workbook = new XSSFWorkbook(byteArrayInputStream);
			
			while(true) {
				
				List<SLinkedHashMap> sheets = excel_data.get_list_slinkedhashmap("sheets");
				
				SLinkedHashMap sheet_data = null;
				for(int sheet_no = 0; sheet_no < read_workbook.getNumberOfSheets(); sheet_no++) {
					
					if(sheets != null) {
						sheet_data = sheets.get(sheet_no);
						if(!sheet_data.is_empty("sheet_name")) {
							read_workbook.setSheetName(sheet_no, sheet_data.get_text("sheet_name"));
						}
					} else {
						sheet_data = new SLinkedHashMap();
					}
					
					write_sheet(read_workbook, read_workbook.getSheetAt(sheet_no), sheet_data);
					
				}// end of sheet
				
				break;
			}// end of while
			
			read_workbook.write(byteArrayOutputStream);
			
		} catch (Exception e) {
			log.error("({}) exception=", request_code, e);
		} finally {
			try {
				if(byteArrayOutputStream != null) {
					excel = byteArrayOutputStream.toByteArray();
					byteArrayOutputStream.close();
				}
				if(byteArrayInputStream != null) {
					byteArrayInputStream.close();
				}
			} catch (IOException e) {
				log.error("({}) exception=", request_code, e);
			}// end of try
		}// end of try
		
		return excel;
	}
	
	private static void write_sheet(
			XSSFWorkbook read_workbook
			, XSSFSheet read_sheet
			, SLinkedHashMap sheet_data
			) {
		
		if("1".equals(sheet_data.get_text("is_map", ""))) {
//			write_sheet_cells(read_sheet, sheet_data);
		} else {
			write_sheet_rows(read_workbook, read_sheet, sheet_data);
		}
		
	}// end of write_sheet
	
	private static int write_sheet_rows(
			XSSFWorkbook read_workbook
			, XSSFSheet read_sheet
			, SLinkedHashMap sheet_data
			) {
		
		int row_no = sheet_data.get_int("row_no");
		int cell_no_max = read_sheet.getRow(row_no).getPhysicalNumberOfCells() - 1;
		
		// style
		short read_row_height = read_sheet.getRow(row_no).getHeight();
		CellStyle[] read_cell_style = cell_style(read_sheet, row_no, cell_no_max);
		
		// remove
		read_sheet.removeRow(read_sheet.getRow(row_no));
		
		List<SLinkedHashMap> rows = sheet_data.get_list_slinkedhashmap("rows");
		if(rows == null) {
			return 1;
		}
		
		XSSFRow write_row = null;
		int cell_no = 0;
		XSSFCell write_cell = null;
		for(SLinkedHashMap row : rows) {
			
			write_row = read_sheet.createRow(row_no++);
			
			write_row.setHeight(read_row_height);
			
			cell_no = 0;
			for(String field_name : row.keys()) {
				
				write_cell = write_row.createCell(cell_no);
				write_cell.setCellValue(row.get_text(field_name));
				write_cell.setCellStyle(read_cell_style[cell_no]);
				cell_no++;
				
			}// end of row
			
		}// end of rows
		
		return 1;
	}// end of write_sheet_rows
	
//	private static int write_sheet_cells(
//			XSSFSheet read_sheet
//			, SLinkedHashMap data
//			) {
//		
//		SLinkedHashMap cells = data.getSLinkedHashMap("cells");
//		
//		return 1;
//	}
	
	private static CellStyle[] cell_style(Sheet sheet, int row_no, int cell_no_max) {
		CellStyle[] cell_style = new CellStyle[cell_no_max + 1];
		for(int cell_no = 0; cell_no <= cell_no_max; cell_no++) {
			cell_style[cell_no] = sheet.getRow(row_no).getCell(cell_no).getCellStyle();
		}
		return cell_style;
	}// end of cell_style
	
//	private static CellStyle[][] cell_style(Sheet sheet, int row_no_min, int row_no_max, int cell_no_max) {
//		CellStyle[][] cell_style = new CellStyle[row_no_max + 1][cell_no_max + 1];
//		for(int row_no = row_no_min; row_no <= row_no_max; row_no++) {
//			cell_style[row_no] = cell_style(sheet, row_no, cell_no_max);
//		}
//		return cell_style;
//	}
	
	public static short[] row_height(Sheet sheet, int row_no_min, int row_no_max) {
		short[] row_height = new short[row_no_max - row_no_min + 1];
		for(int row_no = row_no_min; row_no <= row_no_max; row_no++) {
			row_height[row_no] = sheet.getRow(row_no).getHeight();
		}
		return row_height;
	}// end of row_height
	
	public static int[] column_width(Sheet sheet, int cell_no_min, int cell_no_max) {
		int[] column_width = new int[cell_no_max - cell_no_min + 1];
		for(int cell_no = cell_no_min; cell_no <= cell_no_max; cell_no++) {
			column_width[cell_no] = sheet.getColumnWidth(cell_no);
		}
		return column_width;
	}// end of column_width
	
	public static String cell_value(Cell cell) {
		
		String cell_value = null;
		if(CellType.STRING == cell.getCellTypeEnum()) {
			cell_value = cell.getStringCellValue();
		} else if(CellType.NUMERIC == cell.getCellTypeEnum()) {
			if(DateUtil.isCellDateFormatted(cell)) {
				cell_value = "" + cell.getDateCellValue();
			} else {
				cell_value = NumberToTextConverter.toText(cell.getNumericCellValue());
			}
		} else if(CellType.BOOLEAN == cell.getCellTypeEnum()) {
			cell_value = Boolean.toString(cell.getBooleanCellValue());
		} else if(CellType.FORMULA == cell.getCellTypeEnum()) {
			cell_value = cell.getCellFormula();
		} else if(CellType.BLANK == cell.getCellTypeEnum()) {
			cell_value = "";
		} else if(CellType._NONE == cell.getCellTypeEnum()) {
			cell_value = "";
		}// end of cell type
		
		return cell_value;
	}// end of cell_value
	
}
