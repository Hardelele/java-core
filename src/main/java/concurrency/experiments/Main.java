package concurrency.experiments;

public class Main {

    static volatile int counter = 0;

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    counter++;
                    System.out.println(counter);
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
