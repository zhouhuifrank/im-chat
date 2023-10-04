package com.frankzhou.imchat.common.aop;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.frankzhou.imchat.common.annotation.AuthCheck;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 权限校验切面
 * @date 2023-04-09
 */
@Slf4j
@Aspect
@Component
public class AuthAspect {


    @Pointcut("@annotation(com.frankzhou.imchat.common.annotation.AuthCheck)")
    public void aopPoint(){
    }

    @Around("aopPoint()")
    public Object doAuthCheckInterceptor(ProceedingJoinPoint jp) throws Throwable {
        Method method = getMethod(jp);
        AuthCheck authCheck = method.getAnnotation(AuthCheck.class);

        List<String> anyRoleList = Arrays.stream(authCheck.anyRole()).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        String mustRole = authCheck.mustRole();

        // 拿到请求信息获取当前登录用户
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        /*
        ResultDTO<UserVO> userResult = userService.getLoginUser();
        if (userResult.getResultCode() != 200) {
            throw new BusinessException(ResultCodeConstant.DB_QUERY_NO_DATA);
        }

        // 校验权限
        UserVO loginUser = userResult.getData();
        if (CollectionUtils.isNotEmpty(anyRoleList)) {
            String userRole = loginUser.getRole();
            // 拥有任意权限即可通过
            if (!anyRoleList.contains(userRole)) {
                throw new BusinessException(ResultCodeConstant.NO_AUTH_ERROR);
            }
        }

        if (StringUtils.isNotBlank(mustRole)) {
            String userRole = loginUser.getRole();
            if (!mustRole.equals(userRole)) {
                // 必须拥有该权限才能通过
                throw new BusinessException(ResultCodeConstant.NO_AUTH_ERROR);
            }
        }
        */

        Object result = jp.proceed();
        return result;
    }

    private Method getMethod(ProceedingJoinPoint jp) {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        return methodSignature.getMethod();
    }
}
