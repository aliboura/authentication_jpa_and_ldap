package dz.djezzydevs.auth.auth_ldap_jpa.dto;


import lombok.Data;

@Data
public class AppResponse {


    private Integer id;
    private String appname;
    private String role;
    private int active;
    private String publicapp;
    private String url;
    private String title;
    private String text;
    private String img;

}
