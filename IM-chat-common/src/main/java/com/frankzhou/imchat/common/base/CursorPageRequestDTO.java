package com.frankzhou.imchat.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 游标翻页请求类
 * @date 2023-10-04
 */
@Data
@ApiModel(value = "游标翻页请求基础类")
public class CursorPageRequestDTO {

    @ApiModelProperty(value = "游标，每次请求时需要携带")
    private String cursor;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;
}
