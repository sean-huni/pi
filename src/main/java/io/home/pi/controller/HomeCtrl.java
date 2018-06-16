package io.home.pi.controller;

import io.home.pi.constant.WebCons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.controller
 * USER      : sean
 * DATE      : 12-June-2018
 * TIME      : 22:37
 */
@Controller
@RequestMapping(value = {"/pi**"})
public class HomeCtrl {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeCtrl.class);

    @RequestMapping("/index")
    public ModelAndView homePage() {
        LOGGER.info("Home Page Invoked...");

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("title", WebCons.HOME_PAGE_TITLE);

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addAllObjects(objectMap);
        return modelAndView;
    }
}
