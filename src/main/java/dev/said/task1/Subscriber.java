package dev.said.task1;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.BlockingQueue;

@Getter
@Setter
public class Subscriber implements Runnable {
    private final BlockingQueue<String> tasksQueue;

    public Subscriber(BlockingQueue<String> tasksQueue) {
        System.out.println("New Subscriber created");
        this.tasksQueue = tasksQueue;
    }

    @Override
    public void run() {
        System.out.println("sub: Hello it is subscriber started working");

        try {
            while (true) {

                String task = tasksQueue.take();
                if (task.equalsIgnoreCase("exit")){
                    System.out.println("[" + System.currentTimeMillis() + "] Sub: Exiting");
                    return;
                }
                System.out.println("[" + System.currentTimeMillis() + "]Sub: " + task);

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[" + System.currentTimeMillis() + "]Sub: Some problem while taking tasksQueue object: " + e.getMessage());
        }
    }
}

