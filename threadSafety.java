class Counter1 {
    int count = 0;

    synchronized void increment1() {
        count++;
    }
}

class threadSafety {
    public static void main(String[] args) throws Exception {
        Counter1 c = new Counter1();

        Thread t1 = new Thread(() -> {
            for(int i=0; i<1000; i++) c.increment1();
        });

        Thread t2 = new Thread(() -> {
            for(int i=0; i<1000; i++) c.increment1();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Result: " + c.count);
    }
} 
