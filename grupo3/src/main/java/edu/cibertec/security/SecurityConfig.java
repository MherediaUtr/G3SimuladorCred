package edu.cibertec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
    @Autowired
    private AuthenticationConfiguration authConfig;
    
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //La sesion no queda almacenada en el servidor
        http.csrf(c->c.disable());
        http.authorizeHttpRequests( //Aqui se puede personalizar los acceso de un rol especifico
                    auth ->{
                        auth.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();//Sin autenticar
                        auth.anyRequest().permitAll(); //sin autenticar
                    }
        );
        http.csrf(c -> c.disable());
        /* Generar el token */
        http.addFilterBefore(new GenerarTokenFilter("/api/v1/security", authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class);
        /* Leer el token */
        http.addFilterBefore(new LeerTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
