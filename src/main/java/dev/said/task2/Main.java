package dev.said.task2;

public class Main {
    public static void main(String[] args) {
        SimpleThreadPool stPool = new SimpleThreadPool(10);

        for (int i = 0; i < 20; i++) {
            int taskId = i;
            stPool.submit(() -> {
                try {
                    System.out.println("\nTask with id " + taskId + " started");
                    System.out.println("Thread name for task [" + taskId + "]: " + Thread.currentThread().getName() + "\n");
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            });

        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        stPool.shutdown();
    }
}
