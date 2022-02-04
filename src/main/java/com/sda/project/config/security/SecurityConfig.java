package com.sda.project.config.security;

import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // common
                .antMatchers("/", "/index", "/register/**", "/login").permitAll()

                // static resources
                .antMatchers("/static/favicon.ico", "/images/**", "/js/**", "/css/**", "/images/pet-photos/**").permitAll()

                // user only
                .antMatchers("/home").hasAnyRole("USER")
                .antMatchers("/adoption-add").hasAnyRole("USER")
                .antMatchers("/adoptions-about").hasAnyRole("USER")
                .antMatchers("/my-pets").hasAnyRole("USER")
                .antMatchers("/appointment-add").hasAnyRole("USER")
                .antMatchers("/appointments-about").hasAnyRole("USER")
                .antMatchers("/my-appointments").hasAnyRole("USER")
                .antMatchers("/donation-add").hasAnyRole("USER")
                .antMatchers("/donations-about").hasAnyRole("USER")
                .antMatchers("/pets-user").hasAnyRole("USER")
                .antMatchers("/my-transfers").hasAnyRole("USER")
                .antMatchers("/transfer-add").hasAnyRole("USER")

                // admin only
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/adoptions").hasRole("ADMIN")
                .antMatchers("/appointments").hasRole("ADMIN")
                .antMatchers("/donations").hasRole("ADMIN")
                .antMatchers("/pets").hasRole("ADMIN")
                .antMatchers("/pet-edit").hasRole("ADMIN")
                .antMatchers("/pet-add").hasRole("ADMIN")
                .anyRequest().authenticated();

        // add custom login form
        http.formLogin(form -> form.loginPage("/login").permitAll());

        // login with email as username
        http.formLogin().usernameParameter("email");

        // after successful login go to page
        http.formLogin().successHandler(customAuthSuccessHandler());

        // after logout go to login
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();

        // add a custom access denied handler 404 unauthorized
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(createDaoAuthentication());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider createDaoAuthentication() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    // notifies the session registry that the session is destroyed on logout
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}

