package io.home.pi.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.web.controller
 * USER      : sean
 * DATE      : 05-August-2018
 * TIME      : 09:25
 */
@ExtendWith(MockitoExtension.class)
@Slf4j
public class HomeCtrlTest {

    @Mock
    private Model model;

    private HomeCtrl homeCtrl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        homeCtrl = new HomeCtrl();
    }

    @AfterEach
    public void tearDown() {
        homeCtrl = null;
    }

    @Test
    public void homePage() {
        final String viewName = homeCtrl.homePage(model);
        final ModelMap objectMap = new ModelMap();
        objectMap.put("title", "Pi Home");

        assertEquals("index", viewName);
        verify(model, times(1)).addAllAttributes(objectMap);
    }
}