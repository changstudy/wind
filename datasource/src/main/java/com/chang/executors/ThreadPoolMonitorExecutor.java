package com.chang.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author:ChenChang
 * @Description: 线程池监控
 * @Data: Create in 17:24 2022/11/17
 * @Modified By:
 */
public class ThreadPoolMonitorExecutor extends ThreadPoolExecutor {

    /**
     * record thread start time
     */
    private final ConcurrentHashMap<String, Date> startTimes;
    /**
     * thread pool name
     */
    private final String poolName;
    /**
     * Calculates the total time of thread execution in the thread pool
     */
    private long totalDiff;

    public ThreadPoolMonitorExecutor(String poolName, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        this.startTimes = new ConcurrentHashMap();
        this.poolName = poolName;
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return super.shutdownNow();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        this.startTimes.put(String.valueOf(r.hashCode()), new Date());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Date startDate = this.startTimes.remove(String.valueOf(r.hashCode()));
        Date finishDate = new Date();
        long diff = finishDate.getTime() - startDate.getTime();
        this.totalDiff += diff;
    }

    public long getTotalDiff() {
        return this.totalDiff;
    }
}
