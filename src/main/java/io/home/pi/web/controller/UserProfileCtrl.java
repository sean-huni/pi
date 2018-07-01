package io.home.pi.web.controller;

import io.home.pi.constant.SpringConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.controller
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 19:14
 */
@Slf4j
@Controller
@RequestMapping("/pi")
public class UserProfileCtrl {

    @RequestMapping("/dashboard")
    public String userProfilePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.debug("User-Landing Authenticated Page Invoked...");

        ModelMap objectMap = new ModelMap();

        objectMap.put("subtitle", SpringConstants.PROFILE_PAGE_TITLE);
        objectMap.put("message", String.format("%s, You've just been logged-In!", auth.getName()));
        model.addAllAttributes(objectMap);

        return "dashboard";
    }
}
