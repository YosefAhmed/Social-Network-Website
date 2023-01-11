package spring.learn.SocialMediaApp.security;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests() // ---------|
                .requestMatchers("/authentication/**")//------| (white list) these 3 filters means don't authenticate
                .permitAll()//-----------------------|  the given end points with token.
                .anyRequest()//----------------------|  and authenticate any another requests.
                .authenticated()
                .and()
                .sessionManagement()//------------- to authenticate each request
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Spring will create session for each request
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);//-- Our filter
        return http.build();
    }
}
