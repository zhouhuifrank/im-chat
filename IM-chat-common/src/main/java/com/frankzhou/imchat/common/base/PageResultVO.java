package com.frankzhou.imchat.common.base;

import lombok.Data;

import java.util.List;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 基础翻页返回类
 * @date 2023-10-04
 */
@Data
public class PageResultVO<T> {

    private Integer currentPage;

    private Integer pageSize;

    private Integer totalCount;

    private Boolean isLast;

    private List<T> dataList;
}
