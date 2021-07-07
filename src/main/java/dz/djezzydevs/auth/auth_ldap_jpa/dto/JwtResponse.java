package dz.djezzydevs.auth.auth_ldap_jpa.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtResponse<T> implements Serializable {

    private final T body;
    private boolean success;
    private String message;

    public JwtResponse(T body) {
        this.success = true;
        this.body = body;
        this.message = "Opération effectuée";
    }

    public JwtResponse(boolean success, String message) {
        this.success = success;
        this.body = null;
        this.message = message;
    }

}