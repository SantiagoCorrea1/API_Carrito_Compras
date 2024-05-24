package com.santiago.carrito_compras.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF para pruebas iniciales
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers().permitAll()  // Permitir acceso a /user/sing-up
//                        .anyRequest().authenticated()  // Requerir autenticación para cualquier otra solicitud
//                ).formLogin(form -> form
//                        .loginPage("/login")  // Especificar la página de login
//                        .permitAll()  // Permitir acceso a la página de login para todos
//                )
//                .logout(logout -> logout
//                        .permitAll()  // Permitir logout para todos
//                );
//
//        return http.build();
//    }
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
                            .permitAll())
                    .csrf(AbstractHttpConfigurer::disable);
            return http.build();
}
}
