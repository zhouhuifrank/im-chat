package com.frankzhou.imchat.common.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author This.FrankZhou
 * @since 2023-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("name")
    private String name;

    /**
     * 头像地址
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 性别(男1/女0)
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 手机号
     */
    @TableField("phone")
    private Integer phone;

    /**
     * 微信开放平台open_id
     */
    @TableField("open_id")
    private String openId;

    /**
     * 激活状态
     */
    @TableField("active_status")
    private Integer activeStatus;

    /**
     * 最后一次登录事件
     */
    @TableField("last_opt_time")
    private LocalDateTime lastOptTime;

    /**
     * 用户ip地址信息
     */
    @TableField("ip_info")
    private String ipInfo;

    /**
     * 物品id
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 创建时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
      @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    @TableField("status")
    private String status;


}
