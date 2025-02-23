package fastcampus.auth.config;

import fastcampus.auth.filter.JwtAuthFilter;
import fastcampus.auth.repository.EmployeeRepository;
import fastcampus.auth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final KakaoService kakaoService;
    private final EmployeeRepository employeeRepository;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private static final String[] WHITE_LIST = {"/swagger-ui/**", "/v3/**", "/login/**", "/images/**", "/kakao/callback"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());

        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));

        http.formLogin(AbstractHttpConfigurer::disable);

        http.addFilterBefore(new JwtAuthFilter(kakaoService,employeeRepository), UsernamePasswordAuthenticationFilter.class);
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(WHITE_LIST).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/employees/**").hasRole("USER")
                .requestMatchers("/departments/**").hasRole("USER")
                .anyRequest().authenticated());

        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(customAuthenticationEntryPoint) //인증이 안됐을 때
                .accessDeniedHandler(customAccessDeniedHandler)); //인증은 되었지만 권한이 없을 때

        return http.build();
    }
}
