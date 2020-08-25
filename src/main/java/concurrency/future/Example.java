package concurrency.future;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;

public class Example {
    public static void main(String[] args) throws Exception {
        Example.future();
        Example.completableFuture();
    }

    public static void future() throws Exception {
        Callable<String> callable = () -> Thread.currentThread().toString() + " | Result FutureTask";
        FutureTask<String> future = new FutureTask<>(callable);
        Thread thread = new Thread(future);
        thread.start();
        System.out.println(future.get());
    }

    public static void completableFuture() throws Exception {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() ->
                Thread.currentThread().toString() + " | Result CompletableFuture");
        System.out.println(completableFuture.get());
    }
}