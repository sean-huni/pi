package io.home.pi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
                .antMatchers("/", "/login").permitAll()
                .anyRequest().permitAll()
                .and()
            .authorizeRequests()
                .antMatchers("/pi").hasAnyRole("ROLE_USER")
                .and()

            .formLogin()
                .loginPage("/login")
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
        .and();
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//             User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}