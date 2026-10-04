import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadPools {
    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;

            pool.submit(() -> {
                System.out.println("Task " + taskId + " started by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
                System.out.println("Task " + taskId + " finished by " + Thread.currentThread().getName());
            });
        }

        pool.shutdown();
    }
}