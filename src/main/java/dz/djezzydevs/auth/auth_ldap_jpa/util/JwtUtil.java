package dz.djezzydevs.auth.auth_ldap_jpa.util;
import dz.djezzydevs.auth.auth_ldap_jpa.dao.UserAdRepository;
import dz.djezzydevs.auth.auth_ldap_jpa.entities.RoleEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String SECRET_KEY = "djezzysecretmiftahforitdev";

    @Autowired
    private UserAdRepository userAdRepository;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        List<RoleEntity> roles =userAdRepository.findByWinsessionIgnoreCase(subject).getRoles();
        Set <GrantedAuthority> authorities = new HashSet<GrantedAuthority>(roles.size());

        for (RoleEntity role : roles)
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));



        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .claim("authorities",
                //.claim("ROLES",
                // userAdRepository.get
                        authorities)
                // Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),new SimpleGrantedAuthority("ROLE_PP")))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();


    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && !isTokenBlackListed(token) );
    }

    //addeb by aliboura
    private boolean isTokenBlackListed(String token) {
        return false;
    }
}
