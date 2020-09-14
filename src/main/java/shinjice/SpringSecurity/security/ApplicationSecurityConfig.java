package shinjice.SpringSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static shinjice.SpringSecurity.security.ApplicationUserPermission.*;
import static shinjice.SpringSecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//cross site request forgery
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(KLANT.name())
//                .antMatchers(HttpMethod.DELETE,"management/api/**").hasAuthority(VERKOPER_WRITE.getPermission()) //vervanger  @PreAuthorize
//                .antMatchers(HttpMethod.POST,"management/api/**").hasAuthority(VERKOPER_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"management/api/**").hasAuthority(VERKOPER_WRITE.getPermission())
//                .antMatchers("management/api/**").hasAnyRole(VERKOPER.name(), ADMINISTRATIE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails klantUser = User.builder()
                .username("klant")
                .password(passwordEncoder.encode("password"))
         //       .roles(KLANT.name())
                .authorities(KLANT.getGrandedAuthority())
                .build();

        UserDetails verkoperUser = User.builder()
                .username("verkoper")
                .password(passwordEncoder.encode("password"))
          //      .roles(VERKOPER.name())
                .authorities(VERKOPER.getGrandedAuthority())
                .build();

        UserDetails administratieUser = User.builder()
                .username("administratie")
                .password(passwordEncoder.encode("password"))
          //      .roles(ADMINISTRATIE.name())
                .authorities(ADMINISTRATIE.getGrandedAuthority())
                .build();

        return new InMemoryUserDetailsManager(
                klantUser,
                verkoperUser,
                administratieUser
        );

    }
}
