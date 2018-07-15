package io.home.pi.web.controller;

import io.home.pi.persistence.model.User;
import io.home.pi.service.UserRegService;
import io.home.pi.web.dto.OnRegCompleteEventDTO;
import io.home.pi.web.dto.UserDTO;
import io.home.pi.web.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

import static io.home.pi.constant.SpringConstants.DEBUG_LINE_SEPARATOR;
import static io.home.pi.constant.SpringConstants.DEBUG_LINE_SEPARATOR_ERRORS;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.controller
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 13:05
 * DESCR     : Communication Controller
 */
@Slf4j
@Controller
@RequestMapping("/register")
public class UserRegCtrl extends SuperCtrl {
    private UserRegService userRegService;
    private ApplicationEventPublisher eventPublisher;
    private MessageSource messageSource;

    @Autowired
    public UserRegCtrl(UserRegService userRegService, ApplicationEventPublisher eventPublisher) {
        this.userRegService = userRegService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping(value = "/messageSourceList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getMessageSourceList(@RequestParam(value = "code") List<String> codes) {

        log.info("Codes-Array: {}", codes);

        Map<String, String> hashMap = new HashMap<>();
        for (String code : codes) {
            hashMap.put(code, getMessage(code));
        }
        return ResponseEntity.ok().body(hashMap);
    }


    @Autowired
    @Qualifier("english")
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // Registration
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<GenericResponse> registerUserAccount(@Valid @RequestBody final UserDTO userDTO, final HttpServletRequest request, Errors errors) {
        String responseMsg;
        log.info("Registering user account with information: {}", userDTO);

        if (errors.hasErrors()) {
            log.warn(DEBUG_LINE_SEPARATOR, "Errors!");
            log.warn(DEBUG_LINE_SEPARATOR_ERRORS, Arrays.toString(errors.getAllErrors().toArray()));
            return ResponseEntity.badRequest().body(new GenericResponse(errors.getAllErrors(), "Errors Found"));
        }

        final User registeredUser = userRegService.registerNewUserAccount(userDTO);
        OnRegCompleteEventDTO onRegCompleteEventDTO = new OnRegCompleteEventDTO(registeredUser, Locale.UK, getAppUrl(request));
        log.info(DEBUG_LINE_SEPARATOR, onRegCompleteEventDTO);
        eventPublisher.publishEvent(onRegCompleteEventDTO);
        log.info("Registration Email Sent!!!");

        responseMsg = getMessage("message.regConf");
        return ResponseEntity.ok(new GenericResponse(responseMsg, true));
    }


    private String getMessage(String code) {
        String msg = "";
        try {
            msg = messageSource.getMessage(code, null, Locale.UK);
        } catch (NoSuchMessageException e) {
            return msg;
        }
        return msg;
    }
}
