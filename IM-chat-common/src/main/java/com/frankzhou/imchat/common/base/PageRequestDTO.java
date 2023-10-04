package com.frankzhou.imchat.common.base;

import lombok.Data;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 普通翻页请求类
 * @date 2023-10-04
 */
@Data
public class PageRequestDTO {

    private Integer startPage;

    private Integer pageSize;
}
