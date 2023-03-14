package com.chang.executors;

import com.chang.exception.ApiException;

import java.util.Objects;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import static com.chang.executors.RejectedTypeEnum.*;

/**
 * @Author:ChenChang
 * @Description: RejectHandlerGetter related
 * @Data: Create in 13:40 2022/11/16
 * @Modified By:
 */
public class RejectHandlerGetter {

    private RejectHandlerGetter() {}

    public static RejectedExecutionHandler buildRejectedHandler(String name) {
        if (Objects.equals(name, ABORT_POLICY.getName())) {
            return new ThreadPoolExecutor.AbortPolicy();
        } else if (Objects.equals(name, CALLER_RUNS_POLICY.getName())) {
            return new ThreadPoolExecutor.CallerRunsPolicy();
        } else if (Objects.equals(name, DISCARD_OLDEST_POLICY.getName())) {
            return new ThreadPoolExecutor.DiscardOldestPolicy();
        } else if (Objects.equals(name, DISCARD_POLICY.getName())) {
            return new ThreadPoolExecutor.DiscardPolicy();
        }

        throw new ApiException("Cannot find specified rejectedHandler " + name);
    }

}
