public class realTimeProcessing {

    void processUser(int user) {
        System.out.println("Start processing user " + user);

        try {
            Thread.sleep(500); // simulate work //
        } catch (InterruptedException e) {}

        System.out.println("Finished user " + user);
    }

    void run() {
        for (int i = 0; i < 10; i++) {
            processUser(i);
        }
    }

    public static void main(String[] args) {
        new realTimeProcessing().run();
    }
}