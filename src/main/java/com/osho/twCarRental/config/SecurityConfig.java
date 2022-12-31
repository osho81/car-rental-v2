package com.osho.twCarRental.config;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

// Security configuration, keycloak based

@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    // Keycloak authentication manager
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    @Override
    @ConditionalOnMissingBean(HttpSessionManager.class)
    protected HttpSessionManager httpSessionManager() {
        return new HttpSessionManager();
    }


    /////-------- UNCOMMENT WHEN USING KEYCLOAK ---------/////
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http
//                .authorizeRequests()
//
//                // User access and action rights
//                .antMatchers("/api/v1/cars").hasRole("user")
//                .antMatchers("/api/v1/ordercar").hasRole("user")
//                .antMatchers("/api/v1/updateorder").hasRole("user")
//                .antMatchers("/api/v1/myorders").hasRole("user")
//                .antMatchers("/api/v1/exchange").hasRole("user")
//
//                // Admin access and action rights
//                .antMatchers("/api/v1/customers").hasRole("admin")
//                .antMatchers("/api/v1/addcar").hasRole("admin")
//                .antMatchers("/api/v1/deletecar").hasRole("admin")
//                .antMatchers("/api/v1/updatecar").hasRole("admin")
//                .antMatchers("/api/v1/cancelorder").hasRole("admin")
//
//                .antMatchers("/h2-console/**").permitAll() // For H2-console web view
//
//                .anyRequest().authenticated();
//        http.csrf().disable();
//
//        http.headers().frameOptions().disable(); // For H2-console web view
//
//        http.cors(c ->{
//            CorsConfigurationSource cs = request -> {
//                CorsConfiguration cc = new CorsConfiguration();
//                cc.setAllowedOrigins(List.of("http://127.0.0.1:5500", "http://localhost:5500"));
//                cc.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
//                cc.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
//                return cc;
//            };
//            c.configurationSource(cs);
//        });
//    }

    ///-------- UNCOMMENT WHEN >NOT< USING KEYCLOAK ---------/////
    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()

                // User permitted endpoints
                // (Get car list & update order is partly needed for admin, according to frontend requirements)
                .antMatchers("/api/v1/cars").hasAnyRole("user", "admin")
                .antMatchers("/api/v1/ordercar").hasRole("user")
                .antMatchers("/api/v1/updateorder").hasAnyRole("user", "admin")
                .antMatchers("/api/v1/myorders").hasRole("user")
                .antMatchers("/api/v1/exchange").hasRole("user")

                // Admin permitted endpoints
                .antMatchers("/api/v1/customers").hasRole("admin")
                .antMatchers("/api/v1/addcar").hasRole("admin")
                .antMatchers("/api/v1/deletecar").hasRole("admin")
                .antMatchers("/api/v1/updatecar").hasRole("admin")
                .antMatchers("/api/v1/cancelorder").hasRole("admin")

                // Added to retrieve orders to assist other functions
                .antMatchers("/api/v1/orders").hasAnyRole("admin", "user");


        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.cors(c -> {
            CorsConfigurationSource cs = request -> {
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowedOrigins(List.of("http://127.0.0.1:5500", "http://localhost:5500",
                        "http://127.0.0.1:5501", "http://localhost:5501"));
                cc.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                cc.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "Bearer"));
                return cc;
            };
            c.configurationSource(cs);
        });

    }


}
