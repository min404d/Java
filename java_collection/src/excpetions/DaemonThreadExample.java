package excpetions;
public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread is running...");
                try {
                    Thread.sleep(20000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

       
        daemonThread.setDaemon(true);
        daemonThread.start();

        // Main thread work
        System.out.println("Main thread is finishing");
    }
}