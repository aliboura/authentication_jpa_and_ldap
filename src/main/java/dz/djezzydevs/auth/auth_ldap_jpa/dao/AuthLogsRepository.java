package dz.djezzydevs.auth.auth_ldap_jpa.dao;

import dz.djezzydevs.auth.auth_ldap_jpa.entities.Authlogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthLogsRepository extends JpaRepository<Authlogs,Long > {
}
