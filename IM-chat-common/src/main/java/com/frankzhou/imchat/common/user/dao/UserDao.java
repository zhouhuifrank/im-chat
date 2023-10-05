package com.frankzhou.imchat.common.user.dao;

import com.frankzhou.imchat.common.user.model.entity.User;
import com.frankzhou.imchat.common.user.mapper.UserMapper;
import com.frankzhou.imchat.common.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description mybatis-plus sql管理
 * @date 2023-06-11
 */
@Component
public class UserDao extends ServiceImpl<UserMapper, User> {

}
