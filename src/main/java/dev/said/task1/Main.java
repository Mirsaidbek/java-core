package dev.said.task1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> tasksQueue = new LinkedBlockingQueue<>();

        Publisher publisher = new Publisher(tasksQueue);
        Subscriber subscriber = new Subscriber(tasksQueue);

        // Запускаем в отдельных потоках
        Thread pubThread = new Thread(publisher);
        Thread subThread = new Thread(subscriber);

        pubThread.start();
        subThread.start();
        try {
            pubThread.join();
            subThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
