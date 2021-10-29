package com.stove.server.common.exception.custom;

import com.stove.server.common.exception.CustomException;
import com.stove.server.common.exception.ErrorCode;

/**
 * Created by Minky on 2021-10-30
 */
public class NotFoundUserException extends CustomException {
    public NotFoundUserException() {
        super(ErrorCode.NOT_FOUND_USER);
    }
}
