package com.stove.server.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by Minky on 2021-10-30
 */

@Getter
public enum ErrorCode {
    NOT_FOUND_COMMENT(404, "Comment Data is not found, Please check your Comment ID", HttpStatus.NOT_FOUND),
    NOT_FOUND_BOARD(404, "Board Data is not found, Please check your Board ID", HttpStatus.NOT_FOUND),
    NOT_FOUND_USER(404, "User Data is not found, Please check your User ID", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
