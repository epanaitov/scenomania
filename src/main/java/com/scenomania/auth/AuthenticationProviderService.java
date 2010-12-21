package com.scenomania.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author eugene
 */
//@Service
//public class AuthenticationProviderService extends AnonymousAuthenticationProvider {
public class AuthenticationProviderService implements AuthenticationProvider {
	
	private String key = "soekey";
	
	public boolean supports(Class<? extends Object> type) {
		return true;
	}
	
	//@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		authentication.setAuthenticated(true);
		return authentication;
	}
}
