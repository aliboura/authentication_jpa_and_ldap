package dz.djezzydevs.auth.auth_ldap_jpa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  //active directory
  @Autowired
  private ActiveDirectoryLdapAuthenticationProvider ac;

  @Autowired
  private LdapUserAuthoritiesPopulator ldapUserAuthoritiesPopulator;

//  @Autowired
//  private JwtRequestFilter jwtRequestFilter;


//  @Override
//  public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth
//      .ldapAuthentication()
//        .userDnPatterns("uid={0},ou=people")
//        .groupSearchBase("ou=groups")
//        .contextSource()
//          .url("ldap://localhost:8389/dc=springframework,dc=org")
//          .and()
//        .passwordCompare()
//          .passwordEncoder(new BCryptPasswordEncoder())
//          .passwordAttribute("userPassword").and()
//            // Populates the user roles by LDAP user name from database
//            .ldapAuthoritiesPopulator(ldapUserAuthoritiesPopulator);
//  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .cors().and()
            .csrf().disable()
            .authorizeRequests()
//            .antMatchers(HttpMethod.POST, "/auth")
//            .permitAll()
            .antMatchers(HttpMethod.POST, "/user")
            .permitAll()
         .antMatchers(HttpMethod.POST, "/apps")
          .permitAll()


            .anyRequest().authenticated()
            .and()
            .exceptionHandling().and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
           // httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  ///////////////////
//active directory
 //////////////////

  @Override

  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(ac);

   // auth.
  }

  @Bean
  public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {

    ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider = new ActiveDirectoryLdapAuthenticationProvider(
            "otalgerie.com", "ldap://172.16.61.7:389/");

     // final Collection<GrantedAuthority> authorities = new SimpleGrantedAuthority("ROLE_USER")
    // Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),new SimpleGrantedAuthority("ROLE_PP")))
   // activeDirectoryLdapAuthenticationProvider.setAuthoritiesMapper(authorities);

    return activeDirectoryLdapAuthenticationProvider;
  }


}