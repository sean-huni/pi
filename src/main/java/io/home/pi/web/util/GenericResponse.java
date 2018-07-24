package io.home.pi.web.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Getter
@Setter
public class GenericResponse {
    private String message;
    private String errorMsg;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean successful;
//    private MessageSource messageSource;

    public GenericResponse(final String message, final Boolean successful) {
        super();
        this.message = message;
        this.successful = successful;
    }


    public GenericResponse(final String message, final String errorMsg, final Boolean isSuccessful) {
        super();
        this.message = message;
        this.errorMsg = errorMsg;
        this.successful = isSuccessful;
    }

    public GenericResponse(List<ObjectError> allErrors, String errorMsg) {
        setSuccess(false);
        this.errorMsg = errorMsg;
        String temp = allErrors.stream().map(e -> {
            if (e instanceof FieldError) {
//                return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\"" + stripErrorCode(e.getDefaultMessage()) + "\"}";
                return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
            } else {
//                return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + stripErrorCode(e.getDefaultMessage()) + "\"}";
                return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
            }
        }).collect(Collectors.joining(","));
        this.message = "[" + temp + "]";
    }

//    @Autowired
//    @Qualifier("english")
//    public void setMessageSource(MessageSource messageSource) {
//        this.messageSource = messageSource;
//    }

    public Boolean isSuccess() {
        return successful;
    }

    public void setSuccess(Boolean successful) {
        this.successful = successful;
    }

//    private String stripErrorCode(String errorMsg) {
//        if (errorMsg == null) {
//            return errorMsg;
//        }
//
//        int firstBrace = errorMsg.indexOf("{");
//        if (firstBrace == -1) {
//            return errorMsg;
//        }
//
//        final String messageSourceTxt = messageSource.getMessage(errorMsg.substring(firstBrace + 1, errorMsg.indexOf("}")), null, Locale.UK);
//        log.info("Source-Txt: {}", messageSourceTxt);
//        return messageSourceTxt;
//    }
}
