package com.study.task.scheduledtask.schedule;

import java.util.concurrent.ScheduledFuture;

/**
 * 定时任务ScheduledFuture是ScheduledExecutorService定时任务线程池的执行结果
 *
 * @author ziyuan_deng
 * @create 2020-05-05 12:25
 */
public final class ScheduledTask {
    volatile ScheduledFuture<?> future;

    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
