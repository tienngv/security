package com.tienngv.security.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GpayException extends RuntimeException {

    private final String status;
    private final Object data;

    public GpayException(String status, String message, Object data) {
        super(message); // dùng message từ lớp cha
        this.status = status;
        this.data = data;
    }

    public GpayException(String status, Object data) {
        this.status = status;
        this.data = data;
    }
}

