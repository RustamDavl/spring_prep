package dance.brain.scbtspring.config;

import dance.brain.scbtspring.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        configurer -> configurer
                                .requestMatchers(
                                        "/api/v1/users/registration",
                                        "/api/v1/login",
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**"
                                )
                                .permitAll()
                                .requestMatchers("/api/v1/users/{id}/delete").hasAuthority(Role.ADMIN.getAuthority())
                                .anyRequest()
                                .authenticated()
                )
                //.httpBasic(Customizer.withDefaults())
                .formLogin(
                        configurer -> configurer
                                .loginPage("/api/v1/login")
                                .permitAll()
                                .defaultSuccessUrl("/api/v1/users")
                )
                .logout(configurer -> configurer.logoutUrl("/api/v1/logout"))
                .build();
    }
}
