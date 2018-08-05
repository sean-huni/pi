package io.home.pi.restio;

import io.home.pi.web.dto.device.DeviceDTO;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.restio
 * USER      : sean
 * DATE      : 02-August-2018
 * TIME      : 15:28
 */
public interface RaspPiCtrl {
    DeviceDTO getStatus();
}
