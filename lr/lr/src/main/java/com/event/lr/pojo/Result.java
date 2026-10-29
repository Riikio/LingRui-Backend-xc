package com.event.lr.pojo;

import javax.print.attribute.standard.JobOriginatingUserName;

public class Result {
    private Integer code;   // 状态码：200成功，400失败
    private String msg;
    private Object data;
    public Result() {}

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public static Result success(Object data) {
        return new Result(200, "Success", data);
    }

    public static Result success() {
        return new Result(200, "Success", null);
    }

    public static Result error(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }
}
