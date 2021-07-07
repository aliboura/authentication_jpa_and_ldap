package dz.djezzydevs.auth.auth_ldap_jpa.dao;

import dz.djezzydevs.auth.auth_ldap_jpa.entities.UserAdEntity;
import dz.djezzydevs.auth.auth_ldap_jpa.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAppRepository  extends JpaRepository<UserApp,Integer> {

    List<UserApp> findAllByUsernameIgnoreCase(String user);

  //  UserAdEntity findByWinsessionIgnoreCase(String id);

}
