package io.home.pi.web.dto;

import io.home.pi.persistence.model.User;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.dto
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 16:11
 */
@ToString
@Slf4j
@Getter
@SuppressWarnings("serial")
public class OnRegCompleteEventDTO extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final User user;

    public OnRegCompleteEventDTO(final User user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
        log.debug("OnRegistrationCompleteEvent: {}", toString());
    }

}
