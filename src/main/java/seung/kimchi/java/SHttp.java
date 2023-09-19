package seung.kimchi.java;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.apache.http.HttpStatus;

import kong.unirest.HttpRequest;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class SHttp {

	public static String nslookup(
			String host
			) throws UnknownHostException {
		
		String ipv4 = "";
		
		InetAddress[] inetAddresses = InetAddress.getAllByName(host);
		
		for(InetAddress inetAddress : inetAddresses) {
			ipv4 = inetAddress.getHostAddress();
			break;
		}
		
		return ipv4;
	}// end of nslookup
	
	public static String encode_uri_component(String data, String charset) throws UnsupportedEncodingException {
		return URLEncoder
				.encode(data, charset)
				.replaceAll("\\+", "%20")
				.replaceAll("\\%21", "!")
				.replaceAll("\\%27", "'")
				.replaceAll("\\%28", "(")
				.replaceAll("\\%29", ")")
				.replaceAll("\\%7E", "~")
				;
	}// end of encode_uri_component
	public static String encode_uri_component(String data, Charset charset) throws UnsupportedEncodingException {
		return encode_uri_component(data, charset.name());
	}// end of encode_uri_component
	
	public static String decode_uri(String data, String charset) throws UnsupportedEncodingException {
		return URLDecoder
				.decode(data, charset)
				;
	}// end of decode_uri
	
	public static String content_disposition(
			String user_agent
			, String file_name
			) throws UnsupportedEncodingException {
		
		String prefix = "attachment; file_name=";
		String suffix = "";
		
		switch(browser(user_agent)) {
			case "MSIE":
				suffix = URLEncoder.encode(file_name, "UTF-8").replaceAll("\\+", "%20");
				break;
			case "Chrome":
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i < file_name.length(); i++) {
					char c = file_name.charAt(i);
					if(c > '~') {
						sb.append(URLEncoder.encode("" + c, "UTF-8"));
					} else {
						sb.append(c);
					}
				}
				suffix = sb.toString();
				break;
			case "Opera":
			case "Firefox":
			default:
				suffix = "\"" + new String(file_name.getBytes("UTF-8"), "8859_1") +"\"";
				break;
		}
		
		return prefix.concat(suffix);
	}// end of content_disposition
	
	public static String browser(String user_agent) {
		
		String browser = "";
		
		if(user_agent.indexOf("MSIE") > -1) {
			browser = "MSIE";
		} else if(user_agent.indexOf("Trident") > -1) {
			browser = "MSIE";
		} else if(user_agent.indexOf("Chrome") > -1) {
			browser = "Chrome";
		} else if(user_agent.indexOf("Opera") > -1) {
			browser = "Opera";
		} else {
			browser = "Firefox";
		}
		
		return browser;
	}// end of browser
	
	public static String public_ip(String url) throws InterruptedException, UnsupportedEncodingException {
		
		String public_ip = "";
		
		HttpResponse<String> httpResponse = Unirest
				.get(url)
				.connectTimeout(1000 * 3)
				.socketTimeout(1000 * 60)
				.asString()
				;
		
		if(HttpStatus.SC_OK == httpResponse.getStatus() && httpResponse.getBody() != null) {
			return httpResponse.getBody();
		}
		
		return null;
	}// end of public_ip
	public static String public_ip() throws InterruptedException, UnsupportedEncodingException {
		return public_ip("http://public.restful.kr/ipv4");
	}// end of public_ip
	
}
