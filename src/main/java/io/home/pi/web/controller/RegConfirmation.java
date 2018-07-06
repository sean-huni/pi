package io.home.pi.web.controller;

import io.home.pi.persistence.model.User;
import io.home.pi.persistence.service.UserService;
import io.home.pi.service.UserAuthService;
import io.home.pi.service.UserRegService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.web.controller
 * USER      : sean
 * DATE      : 04-July-2018
 * TIME      : 01:07
 */
@Slf4j
@Controller
public class RegConfirmation {
    private UserRegService userRegService;
    private UserService userService;
    private MessageSource messageSource;
    private final UserAuthService userAuthService;

    @Autowired
    public RegConfirmation(UserRegService userRegService, UserService userService, MessageSource messageSource, UserAuthService userAuthService) {
        this.userRegService = userRegService;
        this.userService = userService;
        this.messageSource = messageSource;
        this.userAuthService = userAuthService;
    }

    //ToDo: Add the missing modelAttributes to the html pages.
    @GetMapping(value = "/confirm**")
    public String confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) {
        Locale locale = request.getLocale();
        final String result = userRegService.validateVerificationToken(token);
        if (result.equals("valid")) {

            final Optional<User> optionalUser = userService.findByToken(token);
            User user = optionalUser.orElseGet(User::new);
            userAuthService.authWithoutPassword(user);
            model.addAttribute("message", messageSource.getMessage("message.accountVerified", null, locale));
            return "redirect:/pi/dashboard?lang=" + locale.getLanguage();
        }

        model.addAttribute("message", messageSource.getMessage("auth.message." + result, null, locale));
        model.addAttribute("expired", "expired".equals(result));
        model.addAttribute("token", token);
        return "redirect:/user/login?error=true&lang=" + locale.getLanguage();
    }

}
