package com.stove.server.common.exception.custom;

import com.stove.server.common.exception.CustomException;
import com.stove.server.common.exception.ErrorCode;

/**
 * Created by Minky on 2021-10-30
 */
public class NotFoundBoardException extends CustomException {
    public NotFoundBoardException() {
        super(ErrorCode.NOT_FOUND_BOARD);
    }
}
