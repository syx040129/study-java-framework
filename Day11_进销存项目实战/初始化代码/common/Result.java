package com.atguigu.liteims.common;

/**
  * 统一接口返回结果类
  * 所有接口返回格式完全一致，解决格式混乱、异常无标准等问题
  * 核心字段：code（业务状态码）、message（提示信息）、data（业务数据）
  */
public class Result<T> {
     /**
      * 业务状态码：200=成功，400=参数错误，500=系统异常（可自定义）
      * 501 = 未登录
      */
     private Integer code;

     /**
      * 提示信息：成功返回"操作成功"，失败返回具体原因（如"手机号已存在"）
      */
     private String message;

     /**
      * 业务数据：列表返回数组、详情返回对象、无数据返回null
      */
     private T data;

     // 私有构造方法，禁止外部直接new，保证返回格式规范
     private Result() {}

     // ========== 手动编写getter/setter方法 ==========
     public Integer getCode() {
         return code;
     }

     public void setCode(Integer code) {
         this.code = code;
     }

     public String getMessage() {
         return message;
     }

     public void setMessage(String message) {
         this.message = message;
     }

     public T getData() {
         return data;
     }

     public void setData(T data) {
         this.data = data;
     }

     // ========== 成功场景快捷方法 ==========
     /**
      * 成功返回（默认提示+业务数据）
      * @param data 业务数据（数组/对象/其他）
      * @return 统一返回结果
      */
     public static <T> Result<T> success(T data) {
         return success("操作成功", data);
     }

     /**
      * 成功返回（自定义提示+业务数据）
      * @param message 自定义成功提示
      * @param data 业务数据
      * @return 统一返回结果
      */
     public static <T> Result<T> success(String message, T data) {
         Result<T> result = new Result<>();
         result.setCode(200);  // 成功固定状态码200
         result.setMessage(message);
         result.setData(data);
         return result;
     }

     // ========== 失败场景快捷方法 ==========
     /**
      * 失败返回（自定义状态码+提示，无数据）
      * @param code 业务状态码（如400/500）
      * @param message 失败具体原因
      * @return 统一返回结果
      */
     public static <T> Result<T> fail(Integer code, String message) {
         Result<T> result = new Result<>();
         result.setCode(code);
         result.setMessage(message);
         result.setData(null);  // 失败场景默认无数据
         return result;
     }

     /**
      * 失败返回（默认系统异常码500+自定义提示）
      * @param message 失败具体原因
      * @return 统一返回结果
      */
     public static <T> Result<T> fail(String message) {
         return fail(500, message);
     }
 }
