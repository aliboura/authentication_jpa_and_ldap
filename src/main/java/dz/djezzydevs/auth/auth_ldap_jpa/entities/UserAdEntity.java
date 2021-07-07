package dz.djezzydevs.auth.auth_ldap_jpa.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employee_ad")
@Data
public class UserAdEntity {

    @Id
    private long id;
    @Column(length=20)
    private String firstname;

    @Column(length=20)
    private String lastname;
    @Column(length=60)
    private String fullname;
    private String position;
    private String department;
    private String location;
    private String email;
    @Column(length=60 )
    private String winsession;
    private String phone;
//    @ManyToMany
//    private List<RoleEntity> roles=new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "adusers_roles",
            joinColumns = @JoinColumn(
                    name = "aduser_id",
                    referencedColumnName = "id",
                    insertable = true,
                    updatable = true
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id",
                    insertable = true,
                    updatable = true)
    )
    private  List<RoleEntity> roles ;
   // AppRole


//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_app",
//            joinColumns = @JoinColumn(
//                    name = "username",
//                    referencedColumnName = "winsession",
//                    insertable = true,
//                    updatable = true
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "app",
//                    referencedColumnName = "appname",
//                    insertable = true,
//                    updatable = true)
//    )
//    private List<AppRole> apps ;


}
