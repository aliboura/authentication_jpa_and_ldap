package dz.djezzydevs.auth.auth_ldap_jpa.dao;

import dz.djezzydevs.auth.auth_ldap_jpa.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Integer> {
}
