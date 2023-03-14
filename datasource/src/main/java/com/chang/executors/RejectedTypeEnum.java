package com.chang.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:ChenChang
 * @Description: 任务策略
 * @Data: Create in 13:53 2022/11/16
 * @Modified By:
 */
public enum  RejectedTypeEnum {

    ABORT_POLICY("AbortPolicy"),
    CALLER_RUNS_POLICY("CallerRunsPolicy"),
    DISCARD_OLDEST_POLICY("DiscardOldestPolicy"),
    DISCARD_POLICY("DiscardPolicy");

    private static final Logger log = LoggerFactory.getLogger(RejectedTypeEnum.class);
    private final String name;

    private RejectedTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
