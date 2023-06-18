package com.tech.zootech.customerservice.utils;

import lombok.experimental.UtilityClass;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@UtilityClass
public class AsyncUtility {
    public int analyzePoolSize(float multiplier) {
        int cores = (int) (Runtime.getRuntime().availableProcessors() * multiplier);
        if (cores <= 1) {
            cores = 25;
        }
        return cores;
    }

    public ThreadPoolTaskExecutor generateExecutor(int cores, int maxPoolMultiplier, int queueCapacity, String threadPrefix) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(cores);
        executor.setMaxPoolSize(cores * maxPoolMultiplier);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadPrefix);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }
}
