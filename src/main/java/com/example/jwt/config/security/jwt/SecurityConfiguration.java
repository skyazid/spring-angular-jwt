package com.example.jwt.config.security.jwt;

import com.example.jwt.config.security.jwt.filter.JwtAuthenticationFilter;
import com.example.jwt.config.security.jwt.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static com.example.jwt.controller.ApplicationRoutes.TASKS;
import static com.example.jwt.entities.security.enums.EnumRole.ADMIN;
import static com.example.jwt.entities.security.enums.EnumRole.USER;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfiguration(@Lazy @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, TASKS + "/**").hasAuthority(USER.name())
                .antMatchers(HttpMethod.POST, TASKS + "/**").hasAuthority(ADMIN.name())
                .antMatchers(HttpMethod.DELETE, TASKS + "/**").hasAuthority(ADMIN.name())
                .antMatchers(HttpMethod.PUT, TASKS + "/**").hasAuthority(ADMIN.name())
                .antMatchers(HttpMethod.PATCH, TASKS + "/**").hasAuthority(ADMIN.name())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        if (SecurityConstants.CORS) {
            httpSecurity.cors();
        }
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        for (String url : SecurityConstants.CORS_URL) {
            config.addAllowedOrigin(url);
        }
        config.addAllowedHeader("*");
        config.addExposedHeader(SecurityConstants.AUTHORIZATION_HEADER);
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
