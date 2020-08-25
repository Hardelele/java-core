package concurrency.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
           return "Hello";
        });
        AnotherClass anotherClass = new AnotherClass();
        CompletableFuture<String> completed = anotherClass.doSomething(completableFuture);
        System.out.println(completed.get());
    }
}

class AnotherClass {
    public CompletableFuture<String> doSomething(CompletableFuture<String> completableFuture) {
        return completableFuture.thenApply((result) -> {
            System.out.println(result + " world!");
            return result + " callback!";
        });
    }
}