package dz.djezzydevs.auth.auth_ldap_jpa.dao;

import dz.djezzydevs.auth.auth_ldap_jpa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);

    UserEntity findById(String id);
}
