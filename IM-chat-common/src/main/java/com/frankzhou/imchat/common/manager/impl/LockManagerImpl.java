package com.frankzhou.imchat.common.manager.impl;

import com.frankzhou.imchat.common.base.ResultCodeConstant;
import com.frankzhou.imchat.common.exception.ChatBusinessException;
import com.frankzhou.imchat.common.manager.LockManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 分布式锁工具类
 * @date 2023-10-04
 */
@Slf4j
@Component
public class LockManagerImpl implements LockManager {

    @Resource
    private RedissonClient redissonClient;

    @Override
    public <T> T executeLockWithThrow(String key, long time, TimeUnit unit, SupplierThrow<T> supplierThrow) throws Throwable {
        RLock lock = redissonClient.getLock(key);
        boolean success = lock.tryLock(time, unit);
        if (!success) {
            throw new ChatBusinessException(ResultCodeConstant.REDISSON_LOCK_LIMIT);
        }

        try {
            return supplierThrow.get();
        } finally {
            lock.unlock();
        }
    }

    @Override
    @SneakyThrows
    public <T> T executeLock(String key, long time, TimeUnit unit, SupplierThrow<T> supplierThrow) {
        return executeLockWithThrow(key,time,unit,supplierThrow);
    }

    @Override
    public <T> void executeLockWithConsumerThrow(String key, long time, TimeUnit unit, MyConsumer<T> consumer) throws Throwable {
        RLock lock = redissonClient.getLock(key);
        boolean success = lock.tryLock(time, unit);
        if (!success) {
            return;
        }

        consumer.accept(null);
    }

    @Override
    @SneakyThrows
    public <T> void executeLockWithConsumer(String key, long time, TimeUnit unit, MyConsumer<T> consumer) {
        executeLockWithConsumerThrow(key,time,unit,consumer);
    }
}
