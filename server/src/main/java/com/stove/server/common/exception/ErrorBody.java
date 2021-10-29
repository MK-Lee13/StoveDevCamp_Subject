package com.stove.server.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by Minky on 2021-10-30
 */

@Getter
public class ErrorBody {
    private int code;
    private String message;
    private HttpStatus httpStatus;

    public ErrorBody(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
