package com.example.jdias.minderaapp;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by jdias on 22/07/2019.
 */

public class CustomThreadPoolExecutor extends ThreadPoolExecutor {


    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);

        Integer activeCount = getActiveCount();
        Integer queueSize = getQueue().size();

    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);

        Integer activeCount = getActiveCount();
        Integer queueSize = getQueue().size();
    }
}
