package excpetions;

public class ThreadExamples {

    static class MyThread extends Thread {
        public void run() {
            System.out.println("MyThread is running: " + Thread.currentThread().getName());
        }
    }

    static class MyRunnable implements Runnable {
        public void run() {
            System.out.println("MyRunnable is running: " + Thread.currentThread().getName());
        }
    }

    static class SleepyThread extends Thread {
        public void run() {
            try {
                System.out.println("SleepyThread sleeping...");
                Thread.sleep(8000);
                System.out.println("SleepyThread woke up!");
            } catch (InterruptedException e) {
                System.out.println("SleepyThread was interrupted!");
            }
        }
    }

    static class DaemonExample extends Thread {
        public void run() {
            while (true) {
                System.out.println("Daemon thread running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MyThread t1 = new MyThread();
        t1.setName("ExtendedThread");
        t1.start();

        Thread t2 = new Thread(new MyRunnable(), "RunnableThread");
        t2.start();

        SleepyThread t3 = new SleepyThread();
        t3.setName("Sleepy");
        t3.start();
        Thread.sleep(1000);
        t3.interrupt();
        
        DaemonExample daemon = new DaemonExample();
        daemon.setDaemon(true);
        daemon.start();

        Thread t4 = new Thread(() -> {
            System.out.println("JoinThread running...");
        }, "JoinThread");

        t4.start();
        t4.join();
        System.out.println("JoinThread has finished.");

      

        Thread highPriority = new Thread(() -> System.out.println("High Priority Thread"));
        Thread lowPriority = new Thread(() -> System.out.println("Low Priority Thread"));
        highPriority.setPriority(Thread.MAX_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);
        highPriority.start();
        lowPriority.start();

        Thread statusThread = new Thread(() -> System.out.println("Status Thread Running"));
        System.out.println("Before start: " + statusThread.getState());
        statusThread.start();
        System.out.println("After start: " + statusThread.getState());
        statusThread.join();
        System.out.println("After join: " + statusThread.getState());

        System.out.println("Current thread: " + Thread.currentThread().getName());

        Thread.sleep(15000);

        System.out.println("Main thread ends.");
    }
}
