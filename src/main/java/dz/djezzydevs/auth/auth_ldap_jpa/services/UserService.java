package dz.djezzydevs.auth.auth_ldap_jpa.services;

import dz.djezzydevs.auth.auth_ldap_jpa.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService  extends UserDetailsService {
   // UserDto createUser(UserDto user);

}
