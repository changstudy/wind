package com.chang.executors;

import lombok.Data;

@Data
public class ThreadPoolDetailInfo {
    //线程池名字
    private String threadPoolName;
    //当前线程池大小
    private Integer poolSize;
    //线程池核心线程数量
    private Integer corePoolSize;
    //线程池生命周期中最大线程数量
    private Integer largestPoolSize;
    //线程池中允许的最大线程数
    private Integer maximumPoolSize;
    //线程池完成的任务数目
    private long completedTaskCount;
    //线程池中当前活跃个数
    private Integer active;
    //当前线程池总共任务个数(完成 + 队列 + 正在进行)
    private long task;
    //线程最大空闲时间
    private long keepAliveTime;
    //当前活跃线程的占比
    private int activePercent;
    //剩余任务队列容量（阻塞队列）
    private Integer residueQueueSize;
    //当前队列中任务的数量
    private Integer queueSize;
    //线程池中任务平均执行时长ms
    private long avgExecuteTime;


    // 总队列长度 = queueSize + residueQueueSize
    // task = active + queueSize + completedTaskCount


    @Override
    public String toString() {
        return "ThreadPoolDetailInfo{" + '\n' +
                " 线程池名字 = " + threadPoolName + '\n' +
                " 当前线程池大小 = " + poolSize + '\n' +
                " 线程池核心线程数量 = " + corePoolSize + '\n' +
                " 线程池生命周期中最大线程数量 = " + largestPoolSize + '\n' +
                " 线程池中允许的最大线程数 = " + maximumPoolSize + '\n' +
                " 线程池完成的任务数目 = " + completedTaskCount + '\n' +
                " 线程池中当前活跃个数 = " + active + '\n' +
                " 当前线程池总共任务个数 = " + task + '\n' +
                " 线程最大空闲时间 = " + keepAliveTime + '\n' +
                " 当前活跃线程的占比 = " + activePercent + '\n' +
                " 剩余任务队列容量 = " + residueQueueSize + '\n' +
                " 当前队列中任务的数量 = " + queueSize + '\n' +
                " 线程池中任务平均执行时长 = " + avgExecuteTime + '\n' +
                '}';
    }
}
