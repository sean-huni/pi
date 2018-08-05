package io.home.pi.restio.impl;

import io.home.pi.restio.RaspPiCtrl;
import io.home.pi.web.dto.device.DeviceDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.restio
 * USER      : sean
 * DATE      : 02-August-2018
 * TIME      : 13:26
 */
@Component
@PropertySource("classpath:application.properties")
public class RaspPiCtrlImpl implements RaspPiCtrl {
    @Value("${raspberry.pi.device.a.id}")
    private String switchID;

    @Value("${raspberry.pi.device.a.auth.token}")
    private String authToken;

    @Value("${raspberry.pi.url}")
    private String url;

    @Override
    public DeviceDTO getStatus() {
        RestTemplate restTemplate = new RestTemplate();
        String deviceURL = "/authToken/" + authToken + "/switchID/" + switchID;
        url += url + deviceURL;
        return restTemplate.getForObject(url, DeviceDTO.class);
    }
}
