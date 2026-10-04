import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class parallelBatchProcessing {

    void processChunk(List<Integer> chunk) {
        System.out.println("\nProcessing CHUNK " + chunk +
                " on " + Thread.currentThread().getName());

        for (int user : chunk) {
            System.out.println(" -> processing user " + user);

            try {
                Thread.sleep(300); // simulate work //
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finished CHUNK " + chunk +
                " on " + Thread.currentThread().getName());
    }

    List<List<Integer>> createChunks(List<Integer> data) {

        int chunkSize = 2; // fixed size = 2 //
        List<List<Integer>> chunks = new ArrayList<>();

        System.out.println("\n=== CREATING CHUNKS (size = 2) ===");

        for (int i = 0; i < data.size(); i += chunkSize) {

            List<Integer> chunk = new ArrayList<>();

            for (int j = i; j < i + chunkSize && j < data.size(); j++) {
                chunk.add(data.get(j));
            }

            System.out.println("Created chunk: " + chunk);
            chunks.add(chunk);
        }

        return chunks;
    }

    void run() {

        // =========================
        // 1. COLLECT PHASE (batch)
        // =========================
        List<Integer> data = new ArrayList<>();

        System.out.println("=== COLLECT BATCH ===");

        for (int i = 0; i < 10; i++) {
            data.add(i);
            System.out.println("Collected user " + i);
        }

        System.out.println("\n=== COLLECTION DONE ===");

        try {
                Thread.sleep(9000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        // =========================
        // 2. CHUNKING PHASE
        // =========================
        List<List<Integer>> chunks = createChunks(data);

        // =========================
        // 3. PARALLEL PROCESSING
        // =========================
        System.out.println("\n=== START PARALLEL PROCESSING ===");

        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (List<Integer> chunk : chunks) {
            pool.submit(() -> processChunk(chunk));
        }

        pool.shutdown();

        try {
            pool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== ALL DONE ===");
    }

    public static void main(String[] args) {
        new parallelBatchProcessing().run();
    }
}