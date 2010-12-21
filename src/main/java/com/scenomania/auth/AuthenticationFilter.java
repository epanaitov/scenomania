package com.scenomania.auth;

import com.scenomania.entities.User;
import com.scenomania.services.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

/**
 *
 * @author eugene
 */
public class AuthenticationFilter extends AnonymousAuthenticationFilter {
	
	@Autowired
	private UserService userService;
	
	@Override
	protected Authentication createAuthentication(HttpServletRequest request) {
		Authentication auth = super.createAuthentication(request);

		Cookie[] cookies = request.getCookies();
		if (cookies == null) return auth;
		for (int i=0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equalsIgnoreCase("scenomania_user") 
				&& (Integer.parseInt(cookie.getValue()) > 0)
				&& userService.ownsThisComputer(Integer.parseInt(cookie.getValue()), request)) {
				
				User user = userService.retrieveUser(Integer.parseInt(cookie.getValue()));
				if (user != null) {
					GrantedAuthority role = new GrantedAuthorityImpl("ROLE_USER");
					List roles = new ArrayList();
					roles.add(role);
					auth = new AnonymousAuthenticationToken("somekey", user, roles);
				}
			}
		}
		
		return auth;
	}
	
}
