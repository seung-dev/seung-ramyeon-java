package seung.kimchi.java;

import java.io.IOException;
import java.util.zip.ZipFile;

public class SFile {

	public static boolean is_zip(String file_path) throws IOException {
		boolean is_zip = false;
		try (
				ZipFile zip_file = new ZipFile(file_path);
				) {
			if(zip_file.size() > 0) {
				is_zip = true;
			}
		}// end of try
		return is_zip;
	}// end of is_zip
	
//	public static void add_zip_entry(
//			ZipOutputStream zipOutputStream
//			, String file_path
//			, byte[] file_data
//			) throws IOException {
//		
//		try (
//				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file_data);
//				ZipEntry zipEntry = new ZipEntry(file_path);
//				) {
//			
//			byteArrayInputStream = new ByteArrayInputStream(file_data);
//			zipEntry = new ZipEntry(file_path);
//			zipOutputStream.putNextEntry(zipEntry);
//			
//			byte[] b = new byte[1024 * 4];
//			int off = 0;
//			int len = 0;
//			while((len = byteArrayInputStream.read(b)) >= 0) {
//				zipOutputStream.write(b, off, len);
//			}
//			
//			zipOutputStream.closeEntry();
//			
//		} catch (FileNotFoundException e) {
//			log.error("exception=", e);
//			throw e;
//		} catch (IOException e) {
//			throw e;
//		} finally {
//			try {
//				if(byteArrayInputStream != null) {
//					byteArrayInputStream.close();
//				}
//			} catch (IOException e) {
//				log.error("exception=", e);
//			}
//		}// end of try
//		
//	}
//	
//	public static byte[] zip(
//			List<String> file_path_list
//			) throws IOException {
//		byte[] zip = null;
//		
//		try (
//				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//				ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
//				) {
//			
//			File file = null;
//			String file_name = null;
//			byte[] file_data = null;
//			for(String file_path : file_path_list) {
//				file = new File(file_path);
//				file_name = file.getName();
//				file_data = FileUtils.readFileToByteArray(file);
//				add_zip_entry(zipOutputStream, file_name, file_data);
//			}
//			
//			zipOutputStream.flush();
//			
//		}// end of try
//		
//		return zip;
//	}// end of zip
//	
//	public static int zip(
//			String zip_path
//			, List<String> file_path_list
//			) {
//		
//		File zip = new File(zip_path);
//		if(zip.exists()) {
//			zip.delete();
//		}
//		
//		FileOutputStream fileOutputStream = null;
//		ZipOutputStream zipOutputStream = null;
//		try {
//			
//			fileOutputStream = new FileOutputStream(zip_path);
//			zipOutputStream = new ZipOutputStream(fileOutputStream);
//			
//			File file = null;
//			String file_name = null;
//			byte[] file_data = null;
//			for(String file_path : file_path_list) {
//				file = new File(file_path);
//				file_name = file.getName();
//				file_data = FileUtils.readFileToByteArray(file);
//				add_zip_entry(zipOutputStream, file_name, file_data);
//			}
//			
//			zipOutputStream.flush();
//			
//		} catch (Exception e) {
//			log.error("exception=", e);
//		} finally {
//			try {
//				if(zipOutputStream != null) {
//					zipOutputStream.close();
//				}
//				if(fileOutputStream != null) {
//					fileOutputStream.close();
//				}
//			} catch (IOException e) {
//				log.error("exception=", e);
//			}
//		}
//		
//		return is_zip(zip_path);
//	}// end of zip
	
}
