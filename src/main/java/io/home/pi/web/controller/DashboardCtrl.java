package io.home.pi.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static io.home.pi.constant.SpringConstants.PROFILE_PAGE_TITLE;
import static java.lang.Boolean.FALSE;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.controller
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 19:14
 * DESCR     : Dashboard Controller
 */
@Slf4j
@Controller
@RequestMapping("/pi**")
public class DashboardCtrl {

    @GetMapping("/dashboard")
    public String userProfilePage(HttpServletRequest request, Model model, @RequestParam(value = "message", required = false) final String msg) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("User-Landing Authenticated Page Invoked...");
        String authenticatedUser = "";
        HttpSession session;

        session = null != request || null != request.getSession(FALSE) ? request.getSession(FALSE) : request.getSession();

        if (session != null && session.isNew()) {
            log.debug("Session is new...");
        } else log.debug("Session is NOT new...");


        if (request != null) {
            authenticatedUser = request.getUserPrincipal() != null ? request.getRemoteUser() : "Anonymous";
        }

        ModelMap objectMap = new ModelMap();

        objectMap.put("subtitle", PROFILE_PAGE_TITLE);
        final String loginMsg = msg != null && !msg.trim().isEmpty() ? msg : "You've just been logged-In!";
        objectMap.put("message", String.format("%s, " + loginMsg, authenticatedUser));
        model.addAllAttributes(objectMap);

        return "dashboard";
    }
}
