package com.stove.server.common.exception;

import lombok.Getter;

/**
 * Created by Minky on 2021-10-30
 */

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
