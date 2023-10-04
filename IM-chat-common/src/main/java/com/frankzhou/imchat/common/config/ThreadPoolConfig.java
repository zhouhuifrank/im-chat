package com.frankzhou.imchat.common.config;

import cn.hutool.extra.spring.SpringUtil;
import com.frankzhou.imchat.common.factory.MyThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description 统一管理线程池
 * @date 2023-06-11
 */
@Slf4j
@Configuration
@EnableAsync
public class ThreadPoolConfig implements AsyncConfigurer {
    /**
     * 项目通用线程池
     */
    public static final String COMMON_EXECUTOR = "commonExecutor";

    /**
     * WebSocket线程池
     */
    public static final String WS_EXECUTOR = "wsExecutor";

    @Override
    public Executor getAsyncExecutor() {
        return commonExecutor();
    }

    @Primary
    @Bean(COMMON_EXECUTOR)
    public ThreadPoolTaskExecutor commonExecutor() {
        // 设置线程池参数
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(200);
        executor.setThreadNamePrefix("common-executor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadFactory(new MyThreadFactory(executor));
        executor.setTaskDecorator(new MyTaskDecorator(COMMON_EXECUTOR));
        executor.setAwaitTerminationMillis(10000);
        executor.initialize();
        return executor;
    }

    @Bean(WS_EXECUTOR)
    public ThreadPoolTaskExecutor rulerExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("ws-executor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadFactory(new MyThreadFactory(executor));
        executor.setTaskDecorator(new MyTaskDecorator(WS_EXECUTOR));
        executor.setAwaitTerminationMillis(10000);
        executor.initialize();
        return executor;
    }

    public class MyTaskDecorator implements TaskDecorator {

        private String executorName;

        public MyTaskDecorator(String executorName) {
            this.executorName = executorName;
        }

        @Override
        public Runnable decorate(Runnable runnable) {
            Runnable decorator = () -> {
                ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) SpringUtil.getBean(executorName);
                int corePoolSize = executor.getCorePoolSize();
                int maxPoolSize = executor.getMaxPoolSize();
                int activeCount = executor.getActiveCount();
                int queueCapacity = executor.getQueueCapacity();
                int queueSize = executor.getQueueSize();
                log.info("threadName:{},核心线程数:{},最大线程数:{},活跃线程数:{},阻塞队列容量:{},阻塞队列当前大小:{}",
                        executorName,corePoolSize,maxPoolSize,activeCount,queueCapacity,queueSize);
                runnable.run();
            };
            return decorator;
        }
    }

}
