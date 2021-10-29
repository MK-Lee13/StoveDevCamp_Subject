package com.stove.server.common.exception;

import com.stove.server.common.exception.custom.NotFoundBoardException;
import com.stove.server.common.exception.custom.NotFoundCommentException;
import com.stove.server.common.exception.custom.NotFoundUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Minky on 2021-10-30
 */

/*
 * Exception handling class
 */

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    ResponseEntity<ErrorBody> customException(CustomException e) {
        ErrorBody errorBody = getErrorBody(e);
        return new ResponseEntity(errorBody, errorBody.getHttpStatus());
    }

    @ExceptionHandler(NotFoundBoardException.class)
    @ResponseBody
    ResponseEntity<ErrorBody> notFoundBoardException(NotFoundBoardException e) {
        ErrorBody errorBody = getErrorBody(e);
        return new ResponseEntity(errorBody, errorBody.getHttpStatus());
    }

    @ExceptionHandler(NotFoundCommentException.class)
    @ResponseBody
    ResponseEntity<ErrorBody> notFoundCommentException(NotFoundCommentException e) {
        ErrorBody errorBody = getErrorBody(e);
        return new ResponseEntity(errorBody, errorBody.getHttpStatus());
    }

    @ExceptionHandler(NotFoundUserException.class)
    @ResponseBody
    ResponseEntity<ErrorBody> notFoundUserException(NotFoundUserException e) {
        ErrorBody errorBody = getErrorBody(e);
        return new ResponseEntity(errorBody, errorBody.getHttpStatus());
    }

    private ErrorBody getErrorBody(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        return new ErrorBody(
                errorCode.getCode(),
                errorCode.getMessage(),
                errorCode.getHttpStatus()
        );
    }
}
