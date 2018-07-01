package io.home.pi.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Controller
@RequestMapping("/user")
public class LoginCtrl {

    @RequestMapping("/login")
    public ModelAndView loginPage(ModelAndView modelAndView){
        log.debug("login-page invoked...");
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("title", "Login");
        modelMap.addAttribute("username-label", "username");
        modelMap.addAttribute("password-label", "password");
        modelAndView.setViewName("login");
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }
}
