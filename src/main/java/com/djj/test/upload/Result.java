package com.djj.test.upload;

import org.springframework.stereotype.Component;

/**
 * Created by djj on 2017/2/10.
 */
@Component
public class Result {
    private String path;
    private String code;
    private String message;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}