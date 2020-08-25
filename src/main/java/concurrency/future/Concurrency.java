package concurrency.future;

import java.util.concurrent.CompletableFuture;

public class Concurrency {
    public static void main(String[] args) {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            for (int counter = 0; counter < 100; counter++) {
                System.out.println(Thread.currentThread().toString() + " | " + counter);
            }
        });
        for (int counter = 0; counter < 100; counter++) {
            System.out.println(Thread.currentThread().toString() + " | " + counter);
        }
    }
}
