package io.home.pi.web.controller;

import io.home.pi.persistence.model.User;
import io.home.pi.service.UserRegService;
import io.home.pi.web.dto.OnRegCompleteEventDTO;
import io.home.pi.web.dto.UserDTO;
import io.home.pi.web.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;

import static io.home.pi.constant.SpringConstants.DEBUG_LINE_SEPARATOR;
import static io.home.pi.constant.SpringConstants.DEBUG_LINE_SEPARATOR_ERRORS;

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
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserRegCtrl(UserRegService userRegService, ApplicationEventPublisher eventPublisher) {
        this.userRegService = userRegService;
        this.eventPublisher = eventPublisher;
    }

    // Registration
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<GenericResponse> registerUserAccount(@Valid @RequestBody final UserDTO userDTO, final HttpServletRequest request, Errors errors) {
        log.info("Registering user account with information: {}", userDTO);

        if (errors.hasErrors()) {
            log.warn(DEBUG_LINE_SEPARATOR, "Errors!");
            log.warn(DEBUG_LINE_SEPARATOR_ERRORS, Arrays.toString(errors.getAllErrors().toArray()));
            return ResponseEntity.badRequest().body(new GenericResponse(errors.getAllErrors(), "Errors Found"));
        }

        final User registered = userRegService.registerNewUserAccount(userDTO);
        OnRegCompleteEventDTO onRegCompleteEventDTO = new OnRegCompleteEventDTO(registered, request.getLocale(), getAppUrl(request));
        log.info(DEBUG_LINE_SEPARATOR, onRegCompleteEventDTO);
        eventPublisher.publishEvent(onRegCompleteEventDTO);
        log.info("Registration Email Sent!!!");

        return ResponseEntity.ok(new GenericResponse("success", true));
    }


}
