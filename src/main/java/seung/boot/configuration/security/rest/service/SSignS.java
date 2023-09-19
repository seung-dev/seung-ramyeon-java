package seung.boot.configuration.security.rest.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import seung.boot.configuration.security.rest.types.SSigninUsername;
import seung.boot.configuration.security.rest.types.SSignupUsername;
import seung.boot.configuration.security.rest.types.SVerifyUsername;
import seung.boot.configuration.security.types.SOAuth2AuthenticationException;
import seung.boot.configuration.web.types.SRequestAttribute;
import seung.boot.configuration.web.types.SResponseBody;

public interface SSignS {

	/**
	 * <h1>Description</h1>
	 * <p>계정 중복확인</p>
	 * 
	 * @author seung
	 * @since 2023.03.05
	 */
	ResponseEntity<SResponseBody> verify_username(SRequestAttribute request_attribute, SVerifyUsername request_body) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <p>계정 회원가입</p>
	 * 
	 * @author seung
	 * @since 2023.03.05
	 */
	ResponseEntity<SResponseBody> signup_username(SRequestAttribute request_attribute, SSignupUsername request_body) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <p>계정 인증</p>
	 * 
	 * @author seung
	 * @since 2023.03.05
	 */
	ResponseEntity<SResponseBody> signin_username(SRequestAttribute request_attribute, SSigninUsername request_body) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <p>계정 인증 성공</p>
	 * 
	 * @author seung
	 * @since 2023.03.05
	 */
	ResponseEntity<SResponseBody> signin_success(SRequestAttribute request_attribute, HttpServletRequest request) throws SOAuth2AuthenticationException;
	
	/**
	 * <h1>Description</h1>
	 * <p>접속종료</p>
	 * 
	 * @author seung
	 * @since 2023.03.05
	 */
	ResponseEntity<SResponseBody> signout(SRequestAttribute request_attribute) throws Exception;
	
}
