package io.home.pi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.config
 * USER      : sean
 * DATE      : 12-June-2018
 * TIME      : 21:30
 */
@Configuration
@EnableWebMvc
@ComponentScan
@PropertySource(value = {"classpath:application.properties"})
public class WebConfig implements WebMvcConfigurer {
    /**
     * Adds resource handlers.
     *
     * @param registry see {@link ResourceHandlerRegistry}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/**", "/static/**", "/webjars/**")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/webjars/");
    }
}
