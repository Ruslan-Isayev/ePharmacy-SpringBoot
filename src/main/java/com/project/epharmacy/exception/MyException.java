package com.project.epharmacy.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MyException extends RuntimeException {

    private Integer code;

    public MyException(Integer code, String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }
}
