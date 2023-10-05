package com.frankzhou.imchat.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 请求返回错误码
 * @date 2023-10-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "错误码实体类")
public class ResultCodeDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    /**
     * 错误状态码
     */
    @ApiModelProperty(value = "错误状态码")
    private Integer code;

    /**
     * 错误英文信息
     */
    @ApiModelProperty(value = "错误英文信息")
    private String message;

    /**
     * 错误中文信息
     */
    @ApiModelProperty(value = "错误中文信息")
    private String messageInfo;
}
