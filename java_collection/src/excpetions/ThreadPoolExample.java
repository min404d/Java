package excpetions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit multiple tasks to the executor
        for (int i = 1; i <= 7; i++) {
            int taskNumber = i;
            executor.submit(() -> {
                System.out.println("Executing task " + taskNumber + " by thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // Simulate task execution time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskNumber + " completed");
            });
        }

        // Shutdown the executor service
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all tasks to finish
        }
        System.out.println("All tasks finished.");
    }
}