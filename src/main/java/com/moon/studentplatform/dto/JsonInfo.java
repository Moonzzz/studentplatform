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
public class JsonInfo {
    public static final Integer OK = 0;
    public static final Integer ERROR = 500;

    private Integer code;
    private String message;
    private Integer total;
    private Object data;

    public JsonInfo(String message, Integer total, Object data) {
        this.code = OK;
        this.message = message;
        this.total = total;
        this.data = data;
    }
    public JsonInfo(Integer code,String message) {
        this.code = OK;
        this.message = message;
    }

    public JsonInfo setCode(Integer code) {
        this.code = code;
        return this;
    }

    public JsonInfo setMessage(String message) {
        this.message = message;
        return this;
    }

    public JsonInfo setUrl(Integer total) {
        this.total = total;
        return this;
    }

    public JsonInfo setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "code:" + code +
                ", message:'" + message + '\'' +
                ", total:'" + total + '\'' +
                ", data:'" + data +
                "\'}";
    }
}
