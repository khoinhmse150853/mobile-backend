package com.prm.mobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExistedEntityException extends RuntimeException {
    static final long serialVersionUID = 1234567L;

    private String code;

    public ExistedEntityException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
