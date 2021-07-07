package dz.djezzydevs.auth.auth_ldap_jpa.controller;



import dz.djezzydevs.auth.auth_ldap_jpa.dao.RoleRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.dao.UserAdRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.dao.UserAppRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.dao.UserRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.entities.RoleEntity;
import dz.djezzydevs.auth.auth_ldap_jpa.entities.UserApp;
import dz.djezzydevs.auth.auth_ldap_jpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {


//    @Autowired
//    PasswordEncoder passwordEncoder;
@Autowired
UserAdRepository userAdRepo;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserAppRepository userAppRepository;

    @GetMapping("roles")
    public List<RoleEntity> roles() {

    //    return userAdRepo.findByWinsessionIgnoreCase("ali.Bouras").getRoles();

       return  roleRepository.findAll();
    }

    @GetMapping("rr")
    public List<UserApp> apps(String user) {

        return userAppRepository.findAllByUsernameIgnoreCase(user);
    }




    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/user")
    public String user() {
        return "hello user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "hello admin";
    }


//    @GetMapping("/hash")
//    public String hashing() {
//
//        return passwordEncoder.encode("ali123").toString();
//        // return "yep";
//    }




}
