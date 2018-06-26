package io.home.pi.config;

import io.home.pi.service.impl.LoginServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static io.home.pi.constant.SpringConstants.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .and()
                .authorizeRequests()
                .antMatchers("/", URL_LOGIN_PAGE).permitAll()
                .anyRequest().permitAll()
                .and()
                .authorizeRequests().antMatchers("/h2").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/pi").hasRole("USER")
                .and()
                .formLogin()
                .loginPage(URL_LOGIN_PAGE)
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
                .tokenValiditySeconds(180)
                .and()
                .authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll()
                .and().csrf().disable()
                .headers().frameOptions().disable();
    }
}