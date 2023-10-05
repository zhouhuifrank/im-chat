package com.frankzhou.imchat.common.manager;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 分布式锁工具类
 * @date 2023-10-04
 */
public interface LockManager {

    <T> T executeLockWithThrow(String key, long time, TimeUnit unit, SupplierThrow<T> supplierThrow) throws Throwable;

    <T> T executeLock(String key, long time, TimeUnit unit, SupplierThrow<T> supplierThrow);

    <T> void executeLockWithConsumerThrow(String key, long time, TimeUnit unit, MyConsumer<T> consumer) throws Throwable;

    <T> void executeLockWithConsumer(String key, long time, TimeUnit unit, MyConsumer<T> consumer);

    /**
     * 函数式接口Supplier生产者
     * 同Supplier提供get方法
     */
    @FunctionalInterface
    public interface SupplierThrow<T> {

        T get() throws Throwable;
    }

    @FunctionalInterface
    public interface MyConsumer<T> {

        void accept(T t) throws Throwable;
    }
}
