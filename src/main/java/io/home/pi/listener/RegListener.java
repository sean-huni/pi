package io.home.pi.listener;

import io.home.pi.mail.MailGunService;
import io.home.pi.persistence.model.User;
import io.home.pi.service.UserRegService;
import io.home.pi.web.dto.OnRegCompleteEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.home.pi.constant.SpringConstants.DEBUG_LINE_SEPARATOR;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.listener
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 16:35
 */
@Slf4j
@Component
public class RegListener implements ApplicationListener<OnRegCompleteEventDTO> {
    private final String KEY_NAME = "name";
    private final String KEY_SEND_TO = "sendTo";
    private final String KEY_SUBJECT = "subject";
    private final String KEY_BODY = "message";
    private UserRegService service;
    private MessageSource messageSource;
    private MailGunService mailService;

    @Autowired
    public RegListener(UserRegService service, MessageSource messageSource, MailGunService mailService) {
        this.service = service;
        this.messageSource = messageSource;
        this.mailService = mailService;
    }

// API

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(OnRegCompleteEventDTO event) {
        log.debug(DEBUG_LINE_SEPARATOR, "Event Received!");
        log.debug(event.toString());
        log.debug(DEBUG_LINE_SEPARATOR, "Event Received!");
        this.registrationEmailRequest(event);
    }

    /**
     * Send out an email tot he client to confirm registration.
     *
     * @param event
     */
    private void registrationEmailRequest(final OnRegCompleteEventDTO event) {
        final User unregisteredUser = event.getUser();
        final String token = UUID.randomUUID().toString();

        final User savedUser = service.createVerificationTokenForUser(unregisteredUser, token);
        log.debug(DEBUG_LINE_SEPARATOR, "Token Created!");

        final Map<?, String> mailProp = constructEmailMessage(event, savedUser, token);
        log.debug(DEBUG_LINE_SEPARATOR, "Email Constructed!");

        mailService.sendEmail(mailProp.get(KEY_NAME), mailProp.get(KEY_SEND_TO), mailProp.get(KEY_BODY), mailProp.get(KEY_SUBJECT));
        log.debug(DEBUG_LINE_SEPARATOR, "Email Sent Out!");
    }


    private final Map<String, String> constructEmailMessage(final OnRegCompleteEventDTO event, final User user, final String token) {
        final String recipientAddress = user.getUsername();
        final String signature = "\n\nKind regards,\nProject Pi Admin,";
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/confirm.html?token=" + token;
        final String message = messageSource.getMessage("message.regSucc", null, event.getLocale());
        final String messageBody = new StringBuilder().append(message).append(" \r\n").append(confirmationUrl).append(signature).toString();


        final Map<String, String> emailConstructs = new HashMap<>();
        emailConstructs.put(KEY_NAME, user.getName());
        emailConstructs.put(KEY_SEND_TO, recipientAddress);
        emailConstructs.put(KEY_SUBJECT, subject);
        emailConstructs.put(KEY_BODY, messageBody);

        return emailConstructs;
    }
}
