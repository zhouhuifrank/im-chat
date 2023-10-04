package com.frankzhou.imchat.common.aop;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.frankzhou.imchat.common.annotation.RedissonLock;
import com.frankzhou.imchat.common.util.SpELUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


/**
 * @author This.FrankZhou
 * @version 1.0
 * @description Redisson分布式锁切面版本2
 * @date 2023-06-10
 */
@Slf4j
@Aspect
@Component
@Order(0) // 分布式锁切面需要先于@Transcational执行
public class RedissonLockAspect {

    @Resource
    private RedissonClient redissonClient;

    @Pointcut("@annotation(com.frankzhou.imchat.common.annotation.RedissonLock)")
    public void aopPoint() {
    }

    @Around("aopPoint()")
    public Object doRedissonLock(ProceedingJoinPoint jp) throws Throwable {
        Method method = getMethod(jp);
        RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);
        String springEl = redissonLock.key();
        String prefix = StringUtils.isBlank(redissonLock.prefixKey()) ? SpELUtil.getMethodKey(method) : redissonLock.prefixKey();
        String key = SpELUtil.parseSpEl(method,jp.getArgs(),springEl);
        String redisKey = prefix + ":" + key;
        return executeLock(redisKey,
                redissonLock.waitTime(),
                redissonLock.unit(),
                jp::proceed);
    }

    private Method getMethod(ProceedingJoinPoint jp) {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        return methodSignature.getMethod();
    }

    private <T> T executeLock(String lockKey, long waitTime, TimeUnit unit, SupplierThrow<T> supplier) throws Throwable {
        RLock lock = redissonClient.getLock(lockKey);
        boolean isLockSuccess = lock.tryLock(waitTime, unit);
        if (!isLockSuccess) {
            // throw new BusinessException(ErrorConstant.LOCK_LIMIT);
        }
        // 切面结束，执行方法
        try {
            return supplier.get();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 函数式接口Supplier生产者
     * 同Supplier提供get方法
     */
    @FunctionalInterface
    public interface SupplierThrow<T> {
        T get() throws Throwable;
    }
}
