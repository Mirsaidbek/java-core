package dev.said.task2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SimpleThreadPool {
    private final int poolSize;
    private final List<MyThread> threadPool;
    private volatile boolean isRunning = true;
    private final Queue<Runnable> tasksQueue;

    public SimpleThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.tasksQueue = new LinkedList<>();
        this.threadPool = new LinkedList<>();

        for (int i = 0; i < poolSize; i++) {
            MyThread myThread = new MyThread("Thread-" + i);
            myThread.start();
            threadPool.add(myThread);
        }
    }

    public void submit(Runnable task) {

        synchronized (tasksQueue) {
            if (isRunning) {
                tasksQueue.add(task);
                tasksQueue.notify();
            } else {
                System.out.println("Error! ThreadPool is closed");
                return;
            }
        }
    }

    public void shutdown() {
        if (!isRunning) {
            System.out.println("ThreadPool already closed");
            return;
        }

        synchronized (tasksQueue) {
            isRunning = false;
            tasksQueue.notifyAll();
        }
        System.out.println("ThreadPool was closed");
    }


    class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {

                synchronized (tasksQueue) {
                    if (!isRunning && tasksQueue.isEmpty()) {
                        System.out.println("Thread :" + getName() + " STOPPED");
                        break;
                    }

                    while (isRunning && tasksQueue.isEmpty()) {
                        try {
                            tasksQueue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Thread :" + getName() + " INTERRUPTED");
                        }
                    }

                    Runnable task = tasksQueue.poll();

                    try {
                        System.out.println("Running task : " + task);

                        task.run();

                        System.out.println("Task finished : " + task);
                    } catch (Exception e) {
                        System.out.println("Exception occurred: " + e.getMessage());
                    }
                }
            }
        }
    }
}
