package io.home.pi.web.exception;

import org.springframework.mail.MailException;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.web.exception
 * USER      : sean
 * DATE      : 01-July-2018
 * TIME      : 07:14
 */
public class CustomMailException extends MailException {
    /**
     * Constructor for MailException.
     *
     * @param msg the detail message
     */
    public CustomMailException(String msg) {
        super(msg);
    }

    /**
     * Constructor for MailException.
     *
     * @param msg   the detail message
     * @param cause the root cause from the mail API in use
     */
    public CustomMailException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
