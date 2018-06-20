package io.home.pi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.controller
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 21:10
 */
@Controller
@RequestMapping("/user")
public class LoginCtrl {
    private static Logger LOGGER = LoggerFactory.getLogger(LoginCtrl.class);

    @RequestMapping("/login")
    public ModelAndView loginPage(ModelAndView modelAndView){
        LOGGER.info("login-page invoked...");
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("title", "Login-Page");
        modelMap.addAttribute("username-label", "username");
        modelMap.addAttribute("password-label", "password");
        modelAndView.setViewName("login");
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }
}
