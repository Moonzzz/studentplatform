package com.moon.studentplatform.dto;

import lombok.Getter;

/**
 * @author Moon
 */
@Getter
public class ErrorInfo<T> {
    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private Integer code;
    private String message;
    private String url;
    private T data;

    public ErrorInfo<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ErrorInfo<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorInfo<T> setUrl(String url) {
        this.url = url;
        return this;
    }

    public ErrorInfo<T> setData(T data) {
        this.data = data;
        return this;
    }
}
