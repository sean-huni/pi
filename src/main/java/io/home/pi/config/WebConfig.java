package io.home.pi.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

import static io.home.pi.constant.SpringConstants.EXTERNAL_URL_RESOURCES;
import static io.home.pi.constant.SpringConstants.INTERNAL_URL_RESOURCES;

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

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.UK);
        return localeResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/messages");
        messageSource.setCacheSeconds(60); //reload messages every 10 seconds
        return messageSource;
    }


    /**
     * Adds resource handlers.
     *
     * @param registry see {@link ResourceHandlerRegistry}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(EXTERNAL_URL_RESOURCES)
                .addResourceLocations(INTERNAL_URL_RESOURCES);
    }

}
