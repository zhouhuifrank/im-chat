package com.frankzhou.imchat.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 基础翻页返回类
 * @date 2023-10-04
 */
@Data
@ApiModel(value = "基础分页通用返回类")
public class PageResultVO<T> {

    @ApiModelProperty(value = "当前页")
    private Integer currentPage;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "数据条数")
    private Integer totalCount;

    @ApiModelProperty(value = "是否是最后一页")
    private Boolean isLast;

    @ApiModelProperty(value = "数据列表")
    private List<T> dataList;
}
