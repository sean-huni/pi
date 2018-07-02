package io.home.pi.web.controller;

import io.home.pi.persistence.model.User;
import io.home.pi.service.UserRegService;
import io.home.pi.web.dto.OnRegCompleteEventDTO;
import io.home.pi.web.dto.UserDTO;
import io.home.pi.web.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.controller
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 13:05
 */
@Slf4j
@Controller
@RequestMapping("/register")
public class UserRegCtrl extends SuperCtrl {
    private UserRegService userRegService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public void setUserRegService(UserRegService userRegService) {
        this.userRegService = userRegService;
    }

    // Registration
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse registerUserAccount(@Valid final UserDTO accountDto, final HttpServletRequest request) {
        log.info("Registering user account with information: {}", accountDto);

        final User registered = userRegService.registerNewUserAccount(accountDto);
        eventPublisher.publishEvent(new OnRegCompleteEventDTO(registered, request.getLocale(), getAppUrl(request)));
        log.info("Registration Email Sent!!!");
        return new GenericResponse("success", true);
    }
}
