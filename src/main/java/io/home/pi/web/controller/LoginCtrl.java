package io.home.pi.web.controller;

import io.home.pi.web.dto.UserDTO;
import io.home.pi.web.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static io.home.pi.constant.SpringConstants.URL_FORGOT_PASS;
import static io.home.pi.constant.SpringConstants.URL_REG_NEW_USER;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
public class LoginCtrl extends SuperCtrl {

    @RequestMapping("/login")
    public ModelAndView loginPage(ModelAndView modelAndView){
        log.debug("login-page invoked...");
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("title", "Login");
        modelMap.addAttribute("username-label", "username");
        modelMap.addAttribute("password-label", "password");
        modelMap.addAttribute("forgotPassUri", URL_FORGOT_PASS);
        modelMap.addAttribute("userRegUri", URL_REG_NEW_USER);
        modelAndView.setViewName("login");
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }

    //FixMe: Define the implementation!
    @RequestMapping(value = "/forgot-pass", method = POST)
    public GenericResponse forgotPassword(@Valid final UserDTO account, final HttpServletRequest request) {

        log.info("Registering user account with information: {}", account);
        log.info("URL-Request Passed: {}", getAppUrl(request));

        return new GenericResponse("success");
    }
}
