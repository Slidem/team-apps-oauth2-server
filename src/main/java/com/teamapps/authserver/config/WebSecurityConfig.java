package com.teamapps.authserver.config;

import com.teamapps.authserver.service.UserPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author Mihai Alexandru
 * @date 24.11.2018
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private UserPasswordEncoder userPasswordEncoder;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService, UserPasswordEncoder userPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.userPasswordEncoder = userPasswordEncoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(userPasswordEncoder.getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        RequestMatcher staticResourcesMatcher = new AntPathRequestMatcher("/static/**");
        RequestMatcher manifestMatcher = new AntPathRequestMatcher("/manifest.json");

        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(staticResourcesMatcher, manifestMatcher)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll();
    }
}
