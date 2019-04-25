package com.moon.studentplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Moon
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo<T> {
    public static final Integer OK = 0;
    public static final Integer ERROR = 500;

    private Integer code;
    private String message;
    private String url;
    private T data;

    public ErrorInfo(String message, String url, T data) {
        this.code = ERROR;
        this.message = message;
        this.url = url;
        this.data = data;
    }

    public ErrorInfo(String message, String url) {
        this.code = ERROR;
        this.message = message;
        this.url = url;
    }

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

    @Override
    public String toString() {
        return "ErrorInfo={" +
                "code:" + code +
                ", message:'" + message + '\'' +
                ", url:'" + url + '\'' +
                ", data:'" + data +
                "\'}";
    }
}
