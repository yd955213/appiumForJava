package com.myAppium.script.c3IOT.comm.entity;

public class ResponseGson<T> {
    public String msg;
    public Integer code;
    public T data;

    @Override
    public String toString() {
        return "ResponseGson{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public ResponseGson<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseGson<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseGson<T> setData(T data) {
        this.data = data;
        return this;
    }
}
