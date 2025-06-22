package excpetions;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class JavaAdvancedExamples {

    // 1. Custom Exception
    static class MyException extends Exception {
        public MyException(String message) {
            super(message);
        }
    }

    // 2. Try-With-Resources
    public static void tryWithResourcesExample() {
        try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
            System.out.println("File content: " + br.readLine());
        } catch (IOException e) {
            System.out.println("IOException caught: " + e.getMessage());
        }
    }

    // 3. Throwing an Exception
    public static void throwExceptionExample() throws IOException {
        throw new IOException("Manually thrown IOException");
    }

    // 4. Declaring Exceptions
    public static void declaredExceptionExample() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("file.txt"));
        System.out.println("First line: " + br.readLine());
        br.close();
    }

    // 5. Chained Exceptions
    public static void chainedExceptionExample() {
        Throwable cause = new IOException("Disk error");
        throw new RuntimeException("Failed to read file", cause);
    }

    // 6. ExecutorService Thread Pool
    public static void threadPoolExample() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++) {
            int taskNumber = i;
            executor.submit(() -> {
                System.out.println("Executing task " + taskNumber + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskNumber + " completed");
            });
        }
        executor.shutdown();
    }

    // 7. Scheduled Thread Pool
    public static void scheduledThreadPoolExample() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        scheduler.schedule(() -> System.out.println("Delayed Task Executed"), 3, TimeUnit.SECONDS);
        scheduler.shutdown();
    }

    // 8. ReentrantLock Example
    private static final ReentrantLock lock = new ReentrantLock();

    public static void reentrantLockExample() {
        lock.lock();
        try {
            System.out.println("Critical section locked by " + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }

    // 9. Wait and Notify Example
    private static final Object obj = new Object();
    private static boolean condition = false;

    public static void waitNotifyExample() {
        Thread waitingThread = new Thread(() -> {
            synchronized (obj) {
                while (!condition) {
                    try {
                        System.out.println("Waiting thread waiting...");
                        obj.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Condition met, thread proceeds.");
            }
        });

        Thread notifyingThread = new Thread(() -> {
            synchronized (obj) {
                condition = true;
                obj.notify();
                System.out.println("Notifying thread sent notify.");
            }
        });

        waitingThread.start();
        try {
            Thread.sleep(1000); // Wait before notifying
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        notifyingThread.start();
    }

    public static void main(String[] args) {
        try {
            // Uncomment the examples one by one to test safely

            // 1. Custom Exception
             throw new MyException("Custom exception thrown!");

            // 2. Try-With-Resources
            // tryWithResourcesExample();

            // 3. Throwing an Exception
            // throwExceptionExample();

            // 4. Declaring Exceptions
            // declaredExceptionExample();

            // 5. Chained Exceptions
            // chainedExceptionExample();

            // 6. ExecutorService Thread Pool
            // threadPoolExample();

            // 7. Scheduled Thread Pool
            // scheduledThreadPoolExample();

            // 8. ReentrantLock Example
            // reentrantLockExample();

            // 9. Wait and Notify
           // waitNotifyExample();

        } catch (Exception e) {
            System.out.println("Caught exception: " + e);
            e.printStackTrace();
        }
    }
}
