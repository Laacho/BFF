package com.tinqinacademy.bff.rest.securiy;

import com.tinqinacademy.comments.core.services.paths.CommentsURLPaths;
import com.tinqinacademy.hotel.core.services.paths.HotelURLPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(configurer ->
                        configurer
                                //Permitted
                                //Swagger
                                .requestMatchers("/v2/api-docs",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui.html",
                                        "/webjars/**",
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**").permitAll()

                                //Get
                                .requestMatchers(
                                        HttpMethod.GET,
                                        HotelURLPaths.GET_ROOMS,
                                        HotelURLPaths.GET_ROOM_ID,
                                        CommentsURLPaths.GET_ROOM_COMMENT).permitAll()

                                //Admin
                                //Post
                                .requestMatchers(
                                        HttpMethod.POST,
                                        HotelURLPaths.POST_REGISTER,
                                        HotelURLPaths.POST_SYSTEM_ROOM
                                ).hasRole("admin")

                                //Get
                                .requestMatchers(
                                        HttpMethod.GET,
                                        HotelURLPaths.GET_REGISTER
                                ).hasRole("admin")

                                //Put
                                .requestMatchers(
                                        HttpMethod.PUT,
                                        HotelURLPaths.PUT_SYSTEM_ROOM_ID
                                ).hasRole("admin")

                                //Patch
                                .requestMatchers(
                                        HttpMethod.PATCH,
                                        HotelURLPaths.PATCH_SYSTEM_ROOM_ID,
                                        CommentsURLPaths.PATCH_HOTEL_COMMENT
                                ).hasRole("admin")

                                //Delete
                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        HotelURLPaths.DELETE_SYSTEM_ROOM_ID,
                                        CommentsURLPaths.DELETE_SYSTEM_COMMENT)
                                .hasRole("admin")
                                .anyRequest()
                                .authenticated()
                )
                //Това означава, че дори да е бил authenticate-нат user-а, ние пак ще го проверим
                //Няма да пазим някакъв негов state от предишно влизане
                .sessionManagement(sessionConfig ->
                        sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}