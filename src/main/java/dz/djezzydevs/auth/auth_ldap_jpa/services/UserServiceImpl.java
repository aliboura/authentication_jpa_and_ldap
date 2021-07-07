package dz.djezzydevs.auth.auth_ldap_jpa.services;


import dz.djezzydevs.auth.auth_ldap_jpa.dao.UserAdRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.dao.UserRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.entities.UserEntity;
import dz.djezzydevs.auth.auth_ldap_jpa.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserAdRepository userAdRepo;

   @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

   @Autowired
   Utils util;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//      UserEntity userEntity=  userAdRepo.findByWinsession(s);
//     if (userEntity == null) throw new UsernameNotFoundException(s);
//     return  new User(userEntity.getEmail(),"userEntity.getEncryptedPassword()"
//     ,new ArrayList<>());
//
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return  new User(s,"ali",authorities);
    }
}
