package com.frankzhou.imchat.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 删除请求类
 * @date 2023-04-08
 */
@Data
public class DeleteRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 单条数据删除
     */
    private Long id;

    /**
     * 多条数据删除 已逗号隔开
     */
    private String ids;
}
