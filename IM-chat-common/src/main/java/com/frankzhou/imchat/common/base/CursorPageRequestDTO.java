package com.frankzhou.imchat.common.base;

import lombok.Data;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 游标翻页请求类
 * @date 2023-10-04
 */
@Data
public class CursorPageRequestDTO {

    private String cursor;

    private Integer pageSize;
}
