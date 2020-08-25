package concurrency.future;

import java.util.concurrent.Callable;

public class CallableExample {
    private static Integer current = 1;
    private static Integer prev = -1;

    static Callable<Integer> callable = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            int next = current + prev;
            prev = current;
            current = next;
            return current;
        }
    };

    public static void main(String[] args) throws Exception {
        SomeClass someClass = new SomeClass();
        someClass.method(callable);
    }
}

class SomeClass {
    public void method(Callable<Integer> callable) throws Exception {
        for (int counter = 0; counter < 20; counter++) {
            System.out.println(callable.call());
        }
    }
}
