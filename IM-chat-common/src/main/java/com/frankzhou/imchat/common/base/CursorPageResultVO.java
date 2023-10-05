package com.frankzhou.imchat.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 游标翻页通用返回类
 * @date 2023-10-04
 */
@Data
@ApiModel(value = "游标翻页通用返回类")
public class CursorPageResultVO<T> {

    @ApiModelProperty(value = "游标，下一次请求需要带上")
    private String cursor;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "是否是最后一页")
    private Boolean isLast;

    @ApiModelProperty(value = "数据列表")
    private List<T> dataList;
}
