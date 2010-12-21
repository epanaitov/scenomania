package com.scenomania.auth;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author eugene
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException, DataAccessException {
		
		GrantedAuthority role = new GrantedAuthorityImpl("xyu");
		Collection roles = new ArrayList();
		roles.add(role);
		org.springframework.security.core.userdetails.User user = new User("user", "pass", true, true, true, true, roles);
		return user;
	}
	
}
