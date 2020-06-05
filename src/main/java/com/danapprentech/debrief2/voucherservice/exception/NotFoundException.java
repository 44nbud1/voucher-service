package com.danapprentech.debrief2.voucherservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "NotFoundException not found")
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public NotFoundException(String message,String status) {

    }

    public NotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}