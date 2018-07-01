package io.home.pi.web.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.web.controller
 * USER      : sean
 * DATE      : 01-July-2018
 * TIME      : 14:00
 */
public class SuperCtrl {

    final String getAppUrl(final HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
