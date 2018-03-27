package org.basic.security.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.basic.security.provider.BasicAuthProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class BasicSecurityConfigurer extends WebSecurityConfigurerAdapter {

	private static final String EXCLUDES = "/status";

	private BasicAuthProvider authProvider = new BasicAuthProvider();

	public BasicSecurityConfigurer(Map<String, String> credentials) {
		List<UsernamePasswordAuthenticationToken> configuredCreds = new ArrayList<UsernamePasswordAuthenticationToken>();
		for (String user : credentials.keySet()) {
			configuredCreds.add(new UsernamePasswordAuthenticationToken(user, credentials.get(user)));
		}
		authProvider.setUserCredentials(configuredCreds);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.csrf().disable().authorizeRequests().antMatchers(EXCLUDES).permitAll().antMatchers("/admin")
				.hasAuthority("admin").antMatchers("/hello").hasAuthority("user").anyRequest().authenticated().and()
				.httpBasic();
	}
}
