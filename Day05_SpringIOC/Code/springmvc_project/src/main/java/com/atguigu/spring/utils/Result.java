package com.atguigu.spring.utils;

import lombok.Data;

@Data
public class Result {
    private Integer code;
    private String message;
    private Object data;

    private Result() {}

    public static Result success(Object data) {
        return success("操作成功", data);
    }

    public static Result success(String message, Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result success(String message) {
        return success(message, null);
    }

    public static Result fail(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    public static Result fail(String message) {
        return fail(500, message);
    }
}