package dz.djezzydevs.auth.auth_ldap_jpa.services;

import dz.djezzydevs.auth.auth_ldap_jpa.dao.AuthLogsRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.entities.Authlogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
public class LogService {


    @Autowired
    AuthLogsRepository authLogsRepository;

    public void login (String username) {
    Authlogs logUsername= new Authlogs();
        logUsername.setUsername(username);
        logUsername.setDatetime(now());
        logUsername.setLogname("Login");
         authLogsRepository.save(logUsername);
       // return  auth;

    }
}
