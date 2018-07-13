package io.home.pi.web.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.util
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 13:14
 */
@Getter
@Setter
public class GenericResponse {
    private String message;
    private String errorMsg;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean successful;

    public GenericResponse(final String message, final Boolean successful) {
        super();
        this.message = message;
        this.successful = successful;
    }

    public GenericResponse(final String message, final String errorMsg, final Boolean successful) {
        super();
        this.message = message;
        this.errorMsg = errorMsg;
        this.successful = successful;
    }

    public GenericResponse(List<ObjectError> allErrors, String errorMsg) {
        setSuccess(false);
        this.errorMsg = errorMsg;
        String temp = allErrors.stream().map(e -> {
            if (e instanceof FieldError) {
                return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
            } else {
                return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
            }
        }).collect(Collectors.joining(","));
        this.message = "[" + temp + "]";
    }


    public Boolean isSuccess() {
        return successful;
    }

    public void setSuccess(Boolean successful) {
        this.successful = successful;
    }
}
