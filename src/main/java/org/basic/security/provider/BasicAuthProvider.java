package org.basic.security.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class BasicAuthProvider implements AuthenticationProvider {

	private List<UsernamePasswordAuthenticationToken> configuredCredentials;

	public void setUserCredentials(List<UsernamePasswordAuthenticationToken> userCreds) {
		this.configuredCredentials = userCreds;
	}

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),
				auth.getCredentials());
		if (configuredCredentials != null && configuredCredentials.contains(authToken)) {
			GrantedAuthority authority = new SimpleGrantedAuthority(auth.getPrincipal().toString());
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(authority);
			return new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), authorities);
		}
		return null;
	}

	public boolean supports(Class<?> arg0) {
		return UsernamePasswordAuthenticationToken.class.equals(arg0);
	}

}
