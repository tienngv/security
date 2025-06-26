package com.tienngv.security.exception;

import lombok.Getter;

@Getter
public class GpayException extends RuntimeException {

    private final String status;
    private final Object data;

    public GpayException(String status, String message, Object data) {
        super(message); // dùng message từ lớp cha
        this.status = status;
        this.data = data;
    }
}

