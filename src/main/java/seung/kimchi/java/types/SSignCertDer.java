package seung.kimchi.java.types;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SSignCertDer extends SType {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private byte[] encoded;
	
	@NotBlank
	private String type;
	
	@NotBlank
	private int version;
	
	@NotBlank
	private String serial_number;
	
	private String signiture_algorithm_oid;
	
	private String signiture_algorithm_name;
	
	private String issuer_dn;
	
	private String subject_dn;
	
	private long not_before;
	
	private long not_after;
	
	private List<String> key_usage;
	
	private String certificate_policy_oid;
	
	private String subject_alternative_name_oid;
	
	private String vid_oid;
	
	private String vid_hash_algorithm_oid;
	
	private String vid;
	
	private String crl_distribution_point;
	
	public X509Certificate x509_certificate() throws IOException, CertificateException {
		X509Certificate x509_certificate = null;
		try(
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encoded);
				) {
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			x509_certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
		} catch (IOException e) {
			throw e;
		} catch (CertificateException e) {
			throw e;
		}
		return x509_certificate;
	}// end of x509_certificate
	
}
