package com.draxter.draxter.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.draxter.draxter.Service.IUsuarioService;

@Configuration
public class SecurityConfiguration {
    @Autowired
    private IUsuarioService iUsuarioService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(iUsuarioService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers(
                "/registrarse",
                "/catalogo",
                "/nosotros",
                "/iniciarSesion",
                "/password/**",
                "/assets/**").permitAll()
                .regexMatchers("").hasRole("USER")
                .regexMatchers("").hasRole("ADMIN")
                .regexMatchers("").hasRole("ASESOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/iniciarSesion")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/salir"))
                .logoutSuccessUrl("/iniciarSesion?salir")
                .permitAll();

        return http.build();
    }
}
