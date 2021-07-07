package dz.djezzydevs.auth.auth_ldap_jpa.security;

import java.util.Collection;
import java.util.HashSet;

import dz.djezzydevs.auth.auth_ldap_jpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;
 

@Component
public class LdapUserAuthoritiesPopulator implements LdapAuthoritiesPopulator {
 //ActiveDirectoryA
    @Autowired
    UserService userDetailsService;
 
    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
        Collection<? extends GrantedAuthority> authorities = new HashSet<>();
        try {
            authorities = userDetailsService.loadUserByUsername   (username).getAuthorities();
        } catch (Exception e) {
            System.out.println("errrr");
        }
        return authorities;
    }
}