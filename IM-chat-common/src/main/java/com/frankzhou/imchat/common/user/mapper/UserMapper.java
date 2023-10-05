package com.frankzhou.imchat.common.user.mapper;

import com.frankzhou.imchat.common.user.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 用戶表sql UserMapper
 * @date 2023-06-11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
