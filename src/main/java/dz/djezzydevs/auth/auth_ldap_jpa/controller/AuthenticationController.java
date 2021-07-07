package dz.djezzydevs.auth.auth_ldap_jpa.controller;


import dz.djezzydevs.auth.auth_ldap_jpa.dao.AuthLogsRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.dao.JdbcTempRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.dao.UserAdRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.dao.UserAppRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.dto.AppResponse;
import dz.djezzydevs.auth.auth_ldap_jpa.dto.AuthenticationRequest;
import dz.djezzydevs.auth.auth_ldap_jpa.dto.AuthenticationResponse;
import dz.djezzydevs.auth.auth_ldap_jpa.dto.Token;
import dz.djezzydevs.auth.auth_ldap_jpa.entities.Authlogs;
import dz.djezzydevs.auth.auth_ldap_jpa.entities.RoleEntity;
import dz.djezzydevs.auth.auth_ldap_jpa.entities.UserApp;
import dz.djezzydevs.auth.auth_ldap_jpa.services.LogService;
import dz.djezzydevs.auth.auth_ldap_jpa.services.MyUserDetailsService;
import dz.djezzydevs.auth.auth_ldap_jpa.services.UserService;
import dz.djezzydevs.auth.auth_ldap_jpa.util.JwtUtil;
import dz.djezzydevs.auth.auth_ldap_jpa.util.TravisAes;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@RestController
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAdRepository userAdRepository;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private LogService logService;

    @Autowired
    UserAppRepository userAppRepository;

    @Autowired
    JdbcTempRepository jdbcTempRepository;



    @GetMapping("/test")
    public String test() {
        List<RoleEntity> roles =userAdRepository.findByWinsessionIgnoreCase("ali.bouras").getRoles();

        String listToString = roles.toString();
       String st= listToString.substring(1, listToString.length() - 1);
       return st;


       // return roles;
    }


    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

     //   return ResponseEntity.ok(new AuthenticationResponse("jwt"));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        }catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

       // return ResponseEntity.ok(new AuthenticationResponse("jwt"));
       // point : the autenticaton succeeded

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
       // return ResponseEntity.ok("ok");
    }



    @PostMapping("user")
    public Token login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        }catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        String token = getJWTToken(authenticationRequest.getUsername());
        Token tokenDto=new Token(token);

        logService.login(authenticationRequest.getUsername());
        return tokenDto;

    }

    private String getJWTToken(String username) {
        String secretKey = "djezzysecretmiftahforitdev";
        String secretEncryption ="appdjz";

        TravisAes travisAes = new TravisAes();
        String encryptedUserName = travisAes.encrypt(secretEncryption, username);

        List<RoleEntity> roles =userAdRepository.findByWinsessionIgnoreCase(username).getRoles();
        String listToString = roles.toString();
        String rolesString= listToString.substring(1, listToString.length() - 1);


        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rolesString);

        String token = Jwts
                .builder()
                .setId(encryptedUserName)
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000))
//                .signWith(SignatureAlgorithm.HS512,
//                        secretKey.getBytes()).compact();
//
                        .signWith(SignatureAlgorithm.HS256, secretKey).compact();


       // return "Bearer " + token;


        return token;

    }

    @PostMapping("apps")
    public List<AppResponse> apps(@RequestBody AuthenticationRequest authenticationRequest) {

       // return userAppRepository.findAllByUsernameIgnoreCase(authenticationRequest.getUsername());

      return   jdbcTempRepository.findAllByUser(authenticationRequest.getUsername());
    }

}
