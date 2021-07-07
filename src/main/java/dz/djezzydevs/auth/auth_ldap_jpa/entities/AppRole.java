package dz.djezzydevs.auth.auth_ldap_jpa.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "appsroles", schema = "public")
public class AppRole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String appname;

    @Column
    private String role;

    @Column
    private int active;

    @Column
    private String publicapp;

    @Column
    private String url;
    @Column
    private String title;

    @Column
    private String text;
}
