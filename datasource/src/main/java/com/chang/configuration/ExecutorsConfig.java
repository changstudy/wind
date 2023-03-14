package com.chang.configuration;


import com.chang.executors.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author:ChenChang
 * @Description: 线程池
 * @Data: Create in 11:04 2022/11/16
 * @Modified By:
 */
public class ExecutorsConfig {

    private static final HashMap<String, ExecutorService> threadPoolExecutorHashMap = new HashMap();
    public static final String GENERAL_THREAD_POOL_NAME = "general_thread_pool";
    public static final String MONITOR_THREAD_POOL_NAME = "monitor_thread_pool";

    public synchronized static ExecutorService generalThreadPool(){
        ExecutorService general_thread_pool = threadPoolExecutorHashMap.get("general_thread_pool");
        if (general_thread_pool != null){
            return general_thread_pool;
        }
        ThreadPoolExecutor poolExecutor = ThreadPoolBuilder
                .newBuilder()
                .corePoolSize(8)
                .maximumPoolSize(12)
                .keepAliveTime(30)
                .timeUnit(TimeUnit.SECONDS)
                .allowCoreThreadTimeOut(true)
                .threadPoolName(GENERAL_THREAD_POOL_NAME)
                .threadFactory("general_thread_factory_")
                .workQueue(QueueTypeEnum.ARRAY_BLOCKING_QUEUE.getName(), 128, false)
                .rejectedExecutionHandler(RejectedTypeEnum.ABORT_POLICY.getName())
                .build();
        threadPoolExecutorHashMap.put(GENERAL_THREAD_POOL_NAME, poolExecutor);
        return poolExecutor;
    }

    public synchronized static ExecutorService monitorThreadPool(){
        ExecutorService general_thread_pool = threadPoolExecutorHashMap.get("monitor_thread_pool");
        if (general_thread_pool!=null){
            return general_thread_pool;
        }
        ExecutorService monitorExecutor= ThreadPoolBuilder
                .newBuilder()
                .corePoolSize(8)
                .maximumPoolSize(12)
                .keepAliveTime(30)
                .timeUnit(TimeUnit.SECONDS)
                .allowCoreThreadTimeOut(true)
                .threadPoolName(MONITOR_THREAD_POOL_NAME)
                .threadFactory("monitor_thread_factory_")
                .workQueue(QueueTypeEnum.ARRAY_BLOCKING_QUEUE.getName(), 64, false)
                .rejectedExecutionHandler(RejectedTypeEnum.ABORT_POLICY.getName())
                .monitor(true)
                .build();
        threadPoolExecutorHashMap.put(MONITOR_THREAD_POOL_NAME, monitorExecutor);
        return monitorExecutor;
    }


    /**
     * 计算当前线程池状态
     * @return
     */
    public static ThreadPoolDetailInfo threadPoolInfo(String poolName) {
        ExecutorService executorService = threadPoolExecutorHashMap.get(poolName);
        if(executorService!=null && executorService instanceof  ThreadPoolMonitorExecutor){
            ThreadPoolMonitorExecutor threadPool = (ThreadPoolMonitorExecutor)executorService;
            ThreadPoolDetailInfo detailInfo = new ThreadPoolDetailInfo();
            BigDecimal activeCount = new BigDecimal(threadPool.getActiveCount());
            BigDecimal maximumPoolSize = new BigDecimal(threadPool.getMaximumPoolSize());
            BigDecimal result = activeCount.divide(maximumPoolSize, 2, 4);
            detailInfo.setThreadPoolName(poolName);
            detailInfo.setPoolSize(threadPool.getPoolSize());
            detailInfo.setCorePoolSize(threadPool.getCorePoolSize());
            detailInfo.setLargestPoolSize(threadPool.getLargestPoolSize());
            detailInfo.setMaximumPoolSize(threadPool.getMaximumPoolSize());
            detailInfo.setCompletedTaskCount(threadPool.getCompletedTaskCount());
            detailInfo.setActive(threadPool.getActiveCount());
            detailInfo.setTask(threadPool.getTaskCount());
            detailInfo.setKeepAliveTime(threadPool.getKeepAliveTime(TimeUnit.MILLISECONDS));
            detailInfo.setActivePercent(new Double(result.doubleValue() * 100).intValue());
            detailInfo.setResidueQueueSize(threadPool.getQueue().remainingCapacity());
            detailInfo.setQueueSize(threadPool.getQueue().size());
            detailInfo.setAvgExecuteTime(threadPool.getTaskCount() == 0L ? 0L : threadPool.getTotalDiff() / threadPool.getTaskCount());
            return detailInfo;
        }
        return null;
    }
}
