public class Main {
  
    private static int anInt = 10;
  
    public static void main(String[] args) {
        System.out.println("Hello world!");
        method();
    }
  
    private static void method() {
        System.out.println("method" + anInt);
    }
}