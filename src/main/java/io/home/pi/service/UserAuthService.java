package io.home.pi.service;

import javax.servlet.http.HttpSession;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service
 * USER      : sean
 * DATE      : 04-July-2018
 * TIME      : 15:05
 */
public interface UserAuthService {
    void authWithoutPassword(String username, HttpSession request);
}
