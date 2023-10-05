package com.frankzhou.imchat.common.exception;


import com.frankzhou.imchat.common.base.ResultCodeDTO;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 自定义异常类
 * @date 2023-04-21
 */
public class ChatBusinessException extends RuntimeException {

    private Integer code;

    private String info;

    public ChatBusinessException(String message) {
        super(message);
    }

    public ChatBusinessException(String message, Integer code, String info) {
        super(message);
        this.info = info;
        this.code = code;
    }

    public ChatBusinessException(ResultCodeDTO codeDTO) {
        super(codeDTO.getMessageInfo());
        this.info = codeDTO.getMessageInfo();
        this.code = codeDTO.getCode();
    }

    public ChatBusinessException(ResultCodeDTO codeDTO, String message) {
        super(message);
        this.code = codeDTO.getCode();
        this.info = codeDTO.getMessageInfo();
    }

    public Integer getCode() {
        return this.code;
    }

    public String getInfo() {
        return this.info;
    }
}
