package dz.djezzydevs.auth.auth_ldap_jpa.dao;

import dz.djezzydevs.auth.auth_ldap_jpa.entities.UserAdEntity;
import dz.djezzydevs.auth.auth_ldap_jpa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdRepository extends JpaRepository<UserAdEntity,Long> {

    UserAdEntity findById(String id);

    UserAdEntity findByWinsessionIgnoreCase(String id);
}
