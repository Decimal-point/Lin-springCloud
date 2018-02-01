package com.lin.dto;

import java.io.Serializable;

/**
 * 定义统一返回的接口
 */
public class ServiceResponse implements Serializable{
    private Double version = Double.valueOf(1.0D);
    private Integer code;
    private String description;
    private Object result;

    public ServiceResponse(){
        this.code = ServiceResponseCode.SUCCESS;
        this.description = "Success";
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
