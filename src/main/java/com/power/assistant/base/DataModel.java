package com.power.assistant.base;

/**
 * @author wuhanhong
 * @date 2018 - 04 - 28
 */
public class DataModel {
    private Integer code;
    private String message;
    private Object data;

    public static DataModel ok(Object data) {
        return new DataModel(1, "操作成功", data);
    }

    public static DataModel error(String msg) {
        return new DataModel(-1, msg);
    }

    private DataModel(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private DataModel(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
