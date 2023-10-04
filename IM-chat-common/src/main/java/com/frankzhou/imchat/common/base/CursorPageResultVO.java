package com.frankzhou.imchat.common.base;

import lombok.Data;

import java.util.List;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 游标翻页基础返回类
 * @date 2023-10-04
 */
@Data
public class CursorPageResultVO<T> {

    private String cursor;

    private Integer pageSize;

    private Boolean isLast;

    private List<T> dataList;
}
