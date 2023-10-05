package com.frankzhou.imchat.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: this.FrankZhou
 * @date: 2012/12/27
 * @description: 前端通用返回类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "前端通用返回类")
public class ApiResult<T> implements Serializable {
    private static final Long serialVersionUID = 1L;

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "返回数据")
    private T data;

    /**
     * 响应状态码 200表示ok 其他表示异常
     */
    @ApiModelProperty(value = "响应码")
    private Integer resultCode;

    /**
     * 错误英文描述
     */
    @ApiModelProperty(value = "错误英文描述")
    private String error;

    /**
     * 错误中文描述
     */
    @ApiModelProperty(value = "错误中文描述")
    private String errorMsg;

    /**
     *  是否请求成功
     */
    @ApiModelProperty(value = "是否成功")
    private Boolean isSuccess;



    public static <T> ApiResult<T> getResult(ResultCodeDTO resultCodeDTO) {
        ApiResult<T> result = new ApiResult<>();
        result.setResultCode(resultCodeDTO.getCode());
        result.setError(resultCodeDTO.getMessage());
        result.setErrorMsg(resultCodeDTO.getMessageInfo());
        result.isSuccess = false;

        return result;
    }

    public static <T> ApiResult<T> getSuccessResult() {
        ApiResult<T> result = new ApiResult<>();
        result.setResultCode(200);
        result.setIsSuccess(true);

        return result;
    }

    public static <T> ApiResult<T> getSuccessResult(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setResultCode(200);
        result.setData(data);
        result.setIsSuccess(true);

        return result;
    }

    public static <T> ApiResult<T> getErrorResult(ResultCodeDTO resultCodeDTO) {
        ApiResult<T> result = new ApiResult<>();
        result.setResultCode(resultCodeDTO.getCode());
        result.setError(resultCodeDTO.getMessage());
        result.setErrorMsg(resultCodeDTO.getMessageInfo());
        result.isSuccess = false;

        return result;
    }


    private Boolean requestSuccessIs() {
        return this.isSuccess == true;
    }
}
