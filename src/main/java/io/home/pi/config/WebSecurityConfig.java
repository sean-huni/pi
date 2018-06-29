package io.home.pi.config;

import io.home.pi.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static io.home.pi.constant.SpringConstants.*;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private PersistentTokenRepository persistenceTokenRepository;

    private UserDetailServiceImpl userDetailsService;

    @Autowired
    public WebSecurityConfig(PersistentTokenRepository persistenceTokenRepository, UserDetailServiceImpl userDetailsService) {
        this.persistenceTokenRepository = persistenceTokenRepository;
        this.userDetailsService = userDetailsService;
    }

//    @Bean
//    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
//        PersistentTokenBasedRememberMeServices persistenceTokenBasedservice = new PersistentTokenBasedRememberMeServices("rememberme", userDetailsService, persistenceTokenRepository);
//        persistenceTokenBasedservice.setAlwaysRemember(true);
//        return persistenceTokenBasedservice;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                .authorizeRequests()
                .antMatchers(URL_LOGIN_PAGE)
                .permitAll() //Adding this line solved it
                .and()
                .authorizeRequests().antMatchers(PI).hasRole(AUTHORITY_USER)
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
                .deleteCookies(COOKIES_SESSION).clearAuthentication(true)
                .permitAll()
                .and()
                .rememberMe().rememberMeCookieName("rememberme").tokenValiditySeconds(180)
                .tokenRepository(persistenceTokenRepository)
                .alwaysRemember(true)
                .useSecureCookie(true).userDetailsService(userDetailsService)
                .and()
                .csrf().disable()
                .headers().frameOptions().disable(); //h2 DB won't work with frameOptions & CSRF enabled.
    }


}