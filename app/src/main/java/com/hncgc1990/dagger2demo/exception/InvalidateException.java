package com.hncgc1990.dagger2demo.exception;

/**
 * Created by Administrator on 2017-4-17.
 * 校验异常
 */
public class InvalidateException extends RuntimeException{

    public static final int USERNAME=1;
    public static final int PASS=2;


    private int errorCode;


    public InvalidateException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
