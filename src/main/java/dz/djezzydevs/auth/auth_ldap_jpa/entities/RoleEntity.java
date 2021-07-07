package dz.djezzydevs.auth.auth_ldap_jpa.entities;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="role")
@Getter
public class RoleEntity {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;

    public String toString() {
        return "ROLE_"+name;
    }



//    @ManyToMany
//    private List<UserEntity> users=new ArrayList<>();
//    @ManyToMany
//    private List<UserAdEntity> adUsers=new ArrayList<>();
}
