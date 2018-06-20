package io.home.pi.config;

import io.home.pi.constant.SecurityCons;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static io.home.pi.constant.WebCons.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .and()
                .authorizeRequests()
                .antMatchers("/", SecurityCons.URL_LOGIN_PAGE).permitAll()
                .anyRequest().permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/pi").hasRole("USER")
                .and()
                .formLogin()
                .loginPage(SecurityCons.URL_LOGIN_PAGE)
                .defaultSuccessUrl(URL_DEFAULT_AUTH_USER_PAGE, true)
                .permitAll()
                .and()
                .sessionManagement()
                .invalidSessionUrl(URL_INVALID_SESSION)
                .and()
                .logout()
                .logoutUrl(URL_LOGOUT)
                .logoutSuccessUrl(URL_LOGOUT_SUCCESSFUL)
                .deleteCookies(COOKIES_SESSION)
                .permitAll()
                .and()
                .rememberMe()
                .key(KEY_REMEMBER_ME)
                .rememberMeParameter(PARAM_REMEMBER_ME)
                .rememberMeCookieName(COOKIE_REMEMBER_ME)
                .tokenValiditySeconds(180);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.builder()
                        .username("username")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}