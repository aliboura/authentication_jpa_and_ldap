package dz.djezzydevs.auth.auth_ldap_jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      //  registry.addMapping("/**");
        registry.addMapping("/**")
                .allowedOrigins("*")
               // .allowedMethods("*")
                .allowedMethods("GET", "POST")
              //  .allowedHeaders("*")
                .allowedMethods("GET", "POST")
                .exposedHeaders("*")
                .allowCredentials(false);
    }


}
