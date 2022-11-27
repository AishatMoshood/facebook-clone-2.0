package com.aishatmoshood.facebookclone.exceptions;

public class EmailNotValidException extends Exception{
    public EmailNotValidException() {
        super();
    }

    public EmailNotValidException(String message) {
        super(message);
    }

    public EmailNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailNotValidException(Throwable cause) {
        super(cause);
    }

    protected EmailNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
