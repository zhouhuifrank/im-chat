package com.frankzhou.imchat.common.user.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 二元美劇
 * @date 2023-10-05
 */
@Getter
@AllArgsConstructor
public enum YesOrNoEnum {

    YES(1,"是"),
    NO(0,"否");

    private Integer code;

    private String desc;

    private static Map<Integer, YesOrNoEnum> enumMap;

    static {
        enumMap = new HashMap<>();
        YesOrNoEnum[] values = YesOrNoEnum.values();
        for (YesOrNoEnum value : values) {
            enumMap.put(value.getCode(), value);
        }
    }

    public YesOrNoEnum getEnumByCode(Integer code) {
        return enumMap.get(code);
    }
}
