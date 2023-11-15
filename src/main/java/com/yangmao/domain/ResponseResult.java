package com.yangmao.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 查询到的结果数据
     */
    private T data;

    public ResponseResult(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
    public ResponseResult(Integer code,T data){
        this.code=code;
        this.data=data;
    }
    public ResponseResult(Integer code,String msg,T data){
        this.msg=msg;
        this.code=code;
        this.data=data;
    }

}
