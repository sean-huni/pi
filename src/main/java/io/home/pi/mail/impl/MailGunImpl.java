package io.home.pi.mail.impl;

import io.home.pi.mail.MailGunService;
import io.home.pi.web.exception.CustomMailException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.Mail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.web.util
 * USER      : sean
 * DATE      : 01-July-2018
 * TIME      : 06:55
 */

@Slf4j
@PropertySource("classpath:application.properties")
@Component
@NoArgsConstructor
public class MailGunImpl implements MailGunService {

    @Value("${mail.webmaster}")
    private String webMasterEmail;

    @Value("${mail.domain}")
    private String domain;

    @Value("${mail.api-key}")
    private String apiKey;

    @Override
    public void sendEmail(String name, String toEmail, String msg, String sbj) throws CustomMailException {
        try {

            final Mail mail = configAndBuildMail(name, toEmail, msg, sbj);

            log.info("Sending Mail...");
            mail.send();
            log.info("Mail Sent!!!");
        } catch (Exception ex) {
            throw new CustomMailException(ex.getMessage(), ex);
        }
    }

    private Mail configAndBuildMail(String name, String toEmail, String msg, String sbj) {
        log.info("Configuring MailGun...");
        Configuration configuration = new Configuration(domain, apiKey, toEmail);
        log.info("Configuration completed!!!");

        return Mail.using(configuration)
                .to(toEmail)
                .from(name, webMasterEmail)
                .subject(sbj)
                .text(msg)
                .build();
    }
}