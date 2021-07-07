package dz.djezzydevs.auth.auth_ldap_jpa.config;

import dz.djezzydevs.auth.auth_ldap_jpa.dao.RoleRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.dao.UserAdRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.entities.RoleEntity;
import dz.djezzydevs.auth.auth_ldap_jpa.entities.UserAdEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    UserAdRepository userAdRepository;

    @Autowired
    RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Override
    public void run(String...args) throws Exception {
//        logger.info("setting roles to users");
//
//      UserAdEntity user=userAdRepository.findByWinsessionIgnoreCase("ali.bouras");
//
//        List<RoleEntity> roles =roleRepository.findAll();
//     user.setRoles(roles);
//    userAdRepository.save(user);
//        logger.info("Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));
    }
}