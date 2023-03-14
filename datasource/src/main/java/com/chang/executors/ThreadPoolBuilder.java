package com.chang.executors;


import io.netty.util.concurrent.DefaultThreadFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.*;

/**
 * @Author:ChenChang
 * @Description: Builder for creating a ThreadPoolExecutor gracefully.
 * @Data: Create in 11:02 2022/11/16
 * @Modified By:
 */
public class ThreadPoolBuilder {

    /**
     * Name of Dynamic ThreadPool.
     */
    private String threadPoolName = "default-pool-name";

    /**
     * The thread pool that is being monitored
     */
    private boolean monitor = false;

    /**
     * CoreSize of ThreadPool.
     */
    private int corePoolSize = 1;

    /**
     * MaxSize of ThreadPool.
     */
    private int maximumPoolSize = Runtime.getRuntime().availableProcessors();

    /**
     * When the number of threads is greater than the core,
     * this is the maximum time that excess idle threads
     * will wait for new tasks before terminating
     */
    private long keepAliveTime = 30;

    /**
     * Timeout unit.
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    /**
     * Blocking queue, see {@link com.chang.executors.QueueTypeEnum}
     */
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(1024);

    /**
     * RejectedExecutionHandler, see {@link com.chang.executors.RejectedTypeEnum}
     */
    private RejectedExecutionHandler rejectedExecutionHandler =  new ThreadPoolExecutor.AbortPolicy();

    /**
     * Default inner thread factory.
     */
    private ThreadFactory threadFactory = new DefaultThreadFactory("default-ft");

    /**
     * If allow core thread timeout.
     */
    private boolean allowCoreThreadTimeOut;


    private ThreadPoolBuilder() {}

    public static ThreadPoolBuilder newBuilder() {
        return new ThreadPoolBuilder();
    }

    public ThreadPoolBuilder threadPoolName(String poolName) {
        this.threadPoolName = poolName;
        return this;
    }

    public ThreadPoolBuilder monitor(boolean monitor){
        this.monitor = monitor;
        return this;
    }


    public ThreadPoolBuilder corePoolSize(int corePoolSize) {
        if (corePoolSize >= 0) {
            this.corePoolSize = corePoolSize;
        }
        return this;
    }

    public ThreadPoolBuilder maximumPoolSize(int maximumPoolSize) {
        if (maximumPoolSize > 0) {
            this.maximumPoolSize = maximumPoolSize;
        }
        return this;
    }

    public ThreadPoolBuilder keepAliveTime(long keepAliveTime) {
        if (keepAliveTime > 0) {
            this.keepAliveTime = keepAliveTime;
        }
        return this;
    }

    public ThreadPoolBuilder timeUnit(TimeUnit timeUnit) {
        if (timeUnit != null) {
            this.timeUnit = timeUnit;
        }
        return this;
    }

    public ThreadPoolBuilder workQueue(String queueName, Integer capacity, Boolean fair) {
        if (StringUtils.isNotBlank(queueName)) {
            workQueue = QueueTypeEnum.buildBlockingQueue(queueName,
                    capacity != null ? capacity : 1024, fair != null && fair);
        }
        return this;
    }

    public ThreadPoolBuilder rejectedExecutionHandler(String rejectedName) {
        if (StringUtils.isNotBlank(rejectedName)) {
            rejectedExecutionHandler = RejectHandlerGetter.buildRejectedHandler(rejectedName);
        }
        return this;
    }

    public ThreadPoolBuilder threadFactory(String prefix) {
        if (StringUtils.isNotBlank(prefix)) {
            threadFactory = new DefaultThreadFactory(prefix);
        }
        return this;
    }

    public ThreadPoolBuilder allowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
        return this;
    }

    public ThreadPoolExecutor build() {
        if (this.monitor){
            return buildMonitor();
        }else{
            return buildCommon();
        }
    }

    public ThreadPoolExecutor buildCommon() {
        return buildCommonExecutor(this);
    }

    public ThreadPoolExecutor buildMonitor() {
        return buildMonitorExecutor(this);
    }



    private ThreadPoolExecutor buildCommonExecutor(ThreadPoolBuilder builder) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                builder.corePoolSize,
                builder.maximumPoolSize,
                builder.keepAliveTime,
                builder.timeUnit,
                builder.workQueue,
                builder.threadFactory,
                builder.rejectedExecutionHandler
        );
        executor.allowCoreThreadTimeOut(builder.allowCoreThreadTimeOut);
        return executor;
    }


    private ThreadPoolExecutor buildMonitorExecutor(ThreadPoolBuilder builder){
        ThreadPoolMonitorExecutor executor = new ThreadPoolMonitorExecutor(
                builder.threadPoolName,
                builder.corePoolSize,
                builder.maximumPoolSize,
                builder.keepAliveTime,
                builder.timeUnit,
                builder.workQueue,
                builder.threadFactory,
                builder.rejectedExecutionHandler
        );
        executor.allowCoreThreadTimeOut(builder.allowCoreThreadTimeOut);
        return executor;
    }
}
