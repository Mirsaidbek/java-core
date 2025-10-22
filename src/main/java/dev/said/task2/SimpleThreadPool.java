package dev.said.task2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

public class SimpleThreadPool {
    private final int poolSize;
    private List<MyThread> threadPool;
    private volatile boolean isRunning;
    private Queue<Runnable> tasksQueue;

    public SimpleThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.tasksQueue = new LinkedList<>();
        this.threadPool = new LinkedList<>();
        this.isRunning = true;
    }


    class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }
        @Override
        public void run() {}
    }
}
