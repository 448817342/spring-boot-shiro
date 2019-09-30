package com.mye.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author zb
 * @date 2019-08-31 16:19
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    public static final ApiResponse OK = new ApiResponse(ApiError.OK);

    private String code;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this.code = error.code();
        this.message = error.msg();
    }

    public ApiResponse(ApiError error, T data) {
        this.code = error.code();
        this.message = error.msg();
        this.data = data;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
