package io.home.pi.web.dto.device;

import io.home.pi.constant.Status;
import io.home.pi.constant.Switch;
import lombok.Data;

import java.io.Serializable;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.web.dto
 * USER      : sean
 * DATE      : 31-July-2018
 * TIME      : 20:29
 */
@Data
abstract class SuperDeviceDTO implements Serializable {
    private Long serialVersionUID = 1L;

    private String name;
    private String uuid;
    private Switch state;
    private Status status;
    private String location;
    private String descr;


    @Override
    public String toString() {
        return "SuperDeviceDTO{" +
                "serialVersionUID=" + serialVersionUID +
                ", name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                ", state=" + state +
                ", status=" + status +
                ", location='" + location + '\'' +
                ", descr='" + descr + '\'' +
                '}';
    }
}
