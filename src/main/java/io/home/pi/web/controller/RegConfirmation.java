package io.home.pi.web.controller;

import io.home.pi.persistence.model.User;
import io.home.pi.persistence.service.UserService;
import io.home.pi.service.UserRegService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
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
@RequestMapping(value = "/confirm**")
public class RegConfirmation {
    private UserRegService userRegService;
    private UserService userService;
    private MessageSource messageSource;

    @Autowired
    public RegConfirmation(UserRegService userRegService, UserService userService, MessageSource messageSource) {
        this.userRegService = userRegService;
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping(value = "/confirm**")
    public String confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
        Locale locale = request.getLocale();
        final String result = userRegService.validateVerificationToken(token);
        if (result.equals("valid")) {

            final Optional<User> optionalUser = userService.findByToken(token);
            // if (user.isUsing2FA()) {
            // model.addAttribute("qr", userService.generateQRUrl(user));
            // return "redirect:/qrcode.html?lang=" + locale.getLanguage();
            // }
            User user = optionalUser.orElseGet(User::new);
//            authWithoutPassword(user);
            model.addAttribute("message", messageSource.getMessage("message.accountVerified", null, locale));
            return "redirect:/console.html?lang=" + locale.getLanguage();
        }

        model.addAttribute("message", messageSource.getMessage("auth.message." + result, null, locale));
        model.addAttribute("expired", "expired".equals(result));
        model.addAttribute("token", token);
        return "redirect:/badUser.html?lang=" + locale.getLanguage();
    }


    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            log.error("Error while login ", e);
        }
    }
//
//    public void authWithAuthManager(HttpServletRequest request, String username, String password) {
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
//        authToken.setDetails(new WebAuthenticationDetails(request));
//        Authentication authentication = authenticationManager.authenticate(authToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        // request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
//    }
//
//    public void authWithoutPassword(User user) {
//        List<Privilege> privileges = user.getRoles().stream().map(role -> role.getPrivileges()).flatMap(list -> list.stream()).distinct().collect(Collectors.toList());
//        List<GrantedAuthority> authorities = privileges.stream().map(p -> new SimpleGrantedAuthority(p.getName())).collect(Collectors.toList());
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }

}
