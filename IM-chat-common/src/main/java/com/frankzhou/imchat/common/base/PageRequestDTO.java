package com.frankzhou.imchat.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 普通翻页请求类
 * @date 2023-10-04
 */
@Data
@ApiModel(value = "普通分页请求类")
public class PageRequestDTO {

    @ApiModelProperty(value = "开始页")
    private Integer startPage;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;
}
