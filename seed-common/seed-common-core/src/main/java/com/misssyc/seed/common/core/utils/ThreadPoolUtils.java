package com.misssyc.seed.common.core.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 李升平
 * @since 2023/12/30 19:02
 **/
public class ThreadPoolUtils {

    //定义全局线程池
    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            8,
            24,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(32),
            //队列满了就把任务交给主线程执行
            new ThreadPoolExecutor.CallerRunsPolicy());

    private ThreadPoolUtils() {}

    /**
     * 使用线程池异步执行任务
     * @param command task
     */
    public static void execute(Runnable command) {
        threadPool.execute(command);
    }

    /**
     * 返回线程池
     * @return 全局线程池
     */
    public static ThreadPoolExecutor pool() {
        return threadPool;
    }
}
