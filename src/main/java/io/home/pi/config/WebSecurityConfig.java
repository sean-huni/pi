package io.home.pi.config;

import io.home.pi.component.CustomAuthenticationFailureHandler;
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
@EnableTransactionManagement
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private PersistentTokenRepository persistenceTokenRepository;

    private UserDetailServiceImpl userDetailsService;

    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    public WebSecurityConfig(PersistentTokenRepository persistenceTokenRepository, UserDetailServiceImpl userDetailsService, CustomAuthenticationFailureHandler authenticationFailureHandler) {
        this.persistenceTokenRepository = persistenceTokenRepository;
        this.userDetailsService = userDetailsService;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                .authorizeRequests()
                .antMatchers(URL_LOGIN_PAGE, SECURITY_PERMIT_ALL_URLS)
                .permitAll() //Adding this line solved it
//                .and()
//                .authorizeRequests().antMatchers(PI).hasRole(AUTHORITY_USER)
                .and()
                .formLogin().passwordParameter(PARAM_PASS_FIELD)
                .loginPage(URL_LOGIN_PAGE)
                .defaultSuccessUrl(URL_DEFAULT_AUTH_USER_PAGE, true)
//                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .sessionManagement()    //.maximumSessions(1).expiredUrl(URL_LOGOUT_SUCCESSFUL)
                //   .and()
                .invalidSessionUrl(URL_INVALID_SESSION)
                .and()
                .logout()
                .logoutUrl(URL_LOGOUT)
                .logoutSuccessUrl(URL_LOGOUT_SUCCESSFUL)
                .deleteCookies(COOKIES_SESSION).clearAuthentication(true)
                .permitAll()
                .and()
                .rememberMe().rememberMeCookieName(COOKIE_REMEMBER_ME).tokenValiditySeconds(180)
                .tokenRepository(persistenceTokenRepository)
                .alwaysRemember(true)
                .useSecureCookie(true).userDetailsService(userDetailsService)
                .and()
                .csrf().disable()
                .headers().frameOptions().disable(); //h2 DB won't work with frameOptions & CSRF enabled.
    }
}