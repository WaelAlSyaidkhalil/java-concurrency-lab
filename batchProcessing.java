import java.util.ArrayList;
import java.util.List;

public class batchProcessing {

    void processUser(int user) {
        System.out.println("Processing user " + user);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
    }

    void run() {
        List<Integer> batch = new ArrayList<>();

        // collect users into a batch //
        for (int i = 0; i < 10; i++) {
            batch.add(i);
            System.out.println("Collected user " + i);
        }

        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {}

        System.out.println("---- START BATCH PROCESSING ----");

        for (int user : batch) {
            processUser(user);
        }
    }

    public static void main(String[] args) {
        new batchProcessing().run();
    }
}