package io.home.pi.mail;

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
    public void sendEmail(String name, String from, String msg, String sbj) throws CustomMailException {
        try {
            configAndSendMail(name, from, msg, sbj);
        } catch (Exception ex) {
            throw new CustomMailException(ex.getMessage(), ex);
        }
    }

    private void configAndSendMail(String name, String from, String msg, String sbj) throws Exception {
        log.info("Configuring MailGun...");
        Configuration configuration = new Configuration(domain, apiKey, from);
        log.info("Configuration completed!!!");

        log.info("Sending Mail...");
        Mail.using(configuration)
                .to(webMasterEmail)
                .from(name, from)
                .subject(sbj)
                .text(msg)
                .build().send();
        log.info("Mail Sent!!!");
    }
}