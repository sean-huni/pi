package io.home.pi.controller;

import io.home.pi.constant.SpringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Controller
public class HomeCtrl {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeCtrl.class);

    @RequestMapping("/")
    public String homePage(Model model) {
        LOGGER.debug("Home Page Invoked...");
        ModelMap objectMap = new ModelMap();

        objectMap.put("title", SpringConstants.HOME_PAGE_TITLE);
        model.addAllAttributes(objectMap);

        return "index";
    }
}
