package dz.djezzydevs.auth.auth_ldap_jpa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

import java.util.Arrays;
import java.util.Collection;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private ActiveDirectoryLdapAuthenticationProvider delegate; // add additional methods to initialize delegate during
																// your configuration

	@Override

	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		Authentication authentication = delegate.authenticate(auth);

		//final Collection<GrantedAuthority> authorities = utilisateurService.loadRolesFromDatabase(name);




		//delegate.
		//authentication.l
		additionalChecks(authentication);
		return auth;

	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

	public void additionalChecks(Authentication authentication) {
		// throw AuthenticationException when it's not allowed


		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		/*
		 * if (!username.equals("ali.bouras")) { throw new
		 * BadCredentialsException("External system authentication failed"); }
		 */

	}

}