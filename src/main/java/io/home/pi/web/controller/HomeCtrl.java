package io.home.pi.web.controller;

import io.home.pi.constant.SpringConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.controller
 * USER      : sean
 * DATE      : 12-June-2018
 * TIME      : 22:37
 */
@Slf4j
@Controller
public class HomeCtrl {

    @RequestMapping("/")
    public String homePage(Model model) {
        log.debug("Home Page Invoked...");
        ModelMap objectMap = new ModelMap();

        objectMap.put("title", SpringConstants.HOME_PAGE_TITLE);
        model.addAllAttributes(objectMap);

        return "index";
    }
}
