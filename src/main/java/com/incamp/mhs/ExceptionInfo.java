package com.incamp.mhs;

import lombok.Getter;

@Getter
public class ExceptionInfo {

    private String url;

    private String message;

    public ExceptionInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }
}
