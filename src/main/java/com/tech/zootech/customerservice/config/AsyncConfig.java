package com.tech.zootech.customerservice.config;

import com.tech.zootech.customerservice.utils.AsyncUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig {
    @Value("${async.onboard.customers.core.pool.multiplier}")
    private float onboardCustomersCorePoolMultiplier;

    @Value("${async.onboard.customers.max.pool.multiplier}")
    private int onboardCustomersMaxPoolMultiplier;

    @Value("${async.onboard.customers.queue.capacity}")
    private int onboardCustomersQueueCapacity;

    @Bean(name = "onboardCustomersExecutor")
    public Executor onboardCustomersExecutor() {
        int cores = AsyncUtility.analyzePoolSize(onboardCustomersCorePoolMultiplier);
        log.info("cores for async process onboardCustomersExecutor: {}", cores);
        return AsyncUtility.generateExecutor(cores, onboardCustomersMaxPoolMultiplier, onboardCustomersQueueCapacity, "onboard-customer-async-");
    }
}
