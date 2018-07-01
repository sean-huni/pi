package io.home.pi.mail;

import io.home.pi.web.exception.CustomMailException;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.web.util
 * USER      : sean
 * DATE      : 01-July-2018
 * TIME      : 07:38
 */
public interface MailGunService {
    void sendEmail(String name, String toEmail, String msg, String sbj) throws CustomMailException;
}
