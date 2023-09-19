package seung.kimchi.java;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

public class SPdf {

	public static byte[] encrypt(
			byte[] data
			, String key
			, boolean allowPrinting
			, boolean allowExtraction
			, boolean allowModifications
			, boolean readOnly
			) {
		
		byte[] pdf = null;
		
		try(
				PDDocument pdDocument = PDDocument.load(data);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				) {
			
			AccessPermission accessPermission = new AccessPermission();
			accessPermission.setCanExtractContent(allowExtraction);
			accessPermission.setCanModify(allowModifications);
			accessPermission.setCanPrint(allowPrinting);
			if(readOnly) {
				accessPermission.setReadOnly();
			}
			/*
			accessPermission.setCanAssembleDocument(false);
			accessPermission.setCanExtractForAccessibility(false);
			accessPermission.setCanFillInForm(false);
			accessPermission.setCanModifyAnnotations(false);
			accessPermission.setCanPrintDegraded(false);
			*/
			
			StandardProtectionPolicy policy = new StandardProtectionPolicy(
					key
					, key
					, accessPermission
					);
			policy.setEncryptionKeyLength(256);
			
			pdDocument.protect(policy);
			pdDocument.save(byteArrayOutputStream);
			
			pdf = byteArrayOutputStream.toByteArray();
			
		} catch (IOException e) {
			throw e;
		}// end of try
		
		return pdf;
	}// end of encrypt
	public static byte[] encrypt(
			byte[] data
			, String key
			) {
		return encrypt(data, key, false, false, false, true);
	}// end of encrypt
	public static byte[] encrypt(
			File file
			, String key
			) {
		return encrypt(FileUtils.readFileToByteArray(file), key);
	}// end of encrypt
	
	public static byte[] decrypt(
			byte[] data
			, String password
			) {
		
		byte[] pdf = null;
		
		try(
				PDDocument pdDocument = PDDocument.load(data, password);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				) {
			
			if(pdDocument.isEncrypted()) {
				pdDocument.setAllSecurityToBeRemoved(true);
			}
			
			pdDocument.save(byteArrayOutputStream);
			
			pdf = byteArrayOutputStream.toByteArray();
			
		} catch (IOException e) {
			log.error("Failed to remove security.", e);
		}
		
		return pdf;
	}// end of decrypt
	
}
