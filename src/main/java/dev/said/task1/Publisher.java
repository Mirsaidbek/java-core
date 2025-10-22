package dev.said.task1;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

@Getter
@Setter
public class Publisher implements Runnable {
    private final BlockingQueue<String> tasksQueue;

    public Publisher(BlockingQueue<String> tasksQueue) {
        System.out.println("New Publisher created");
        this.tasksQueue = tasksQueue;
    }

    @Override
    public void run() {
        System.out.println("pub: Hello it is publisher started working");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("\n[" + System.currentTimeMillis() + "] Pub: Enter task (\"exit\" to finish)>> ");

                String newTask = scanner.nextLine();
                tasksQueue.put(newTask);

                System.out.println("[" + System.currentTimeMillis() + "] Pub: Added task: (" + newTask + ")\n");

                if (newTask.equalsIgnoreCase("exit")) {
                    System.out.println("[" + System.currentTimeMillis() + "] Pub: Exiting");
                    return;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[" + System.currentTimeMillis() + "] Pub| some error: " + e.getMessage());
        }
    }

}
