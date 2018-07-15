package io.home.pi.web.controller;

import javax.servlet.http.HttpServletRequest;

import static io.home.pi.constant.SpringConstants.HTTP_URL_PREFIX;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.web.controller
 * USER      : sean
 * DATE      : 01-July-2018
 * TIME      : 14:00
 */
class SuperCtrl {

    final String getAppUrl(final HttpServletRequest request) {
        return HTTP_URL_PREFIX + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
