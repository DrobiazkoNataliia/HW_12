import java.util.ArrayList;
import java.util.List;

public class NumberThreadTests_Task2 {
    public static void main(String[] args) {
        ProcessThread_Task2 fizzbuzz = new ProcessThread_Task2((n) -> {
            if (n % 15 == 0) {
                System.out.println("fizzbuzz");
            }
        });
        ProcessThread_Task2 fizz = new ProcessThread_Task2((n) -> {
            if (n % 3 == 0) {
                System.out.println("fizz");
            }
        });
        ProcessThread_Task2 buzz = new ProcessThread_Task2((n) -> {
            if (n % 5 == 0) {
                System.out.println("buzz");
            }
        });
        ProcessThread_Task2 number = new ProcessThread_Task2((n) -> {
            if (n % 3 != 0 && n % 5 != 0) {
                System.out.println(n);
            }
        });
        List<ProcessThread_Task2> threads = new ArrayList<>();
        threads.add(fizzbuzz);
        threads.add(fizz);
        threads.add(buzz);
        threads.add(number);

        for (ProcessThread_Task2 thread : threads) {
            thread.start();
        }

        for (int i = 1; i < 100; i++) {
            for (ProcessThread_Task2 thread : threads) {
                thread.process(i);
            }
            while (true) {
                int processedCount = 0;
                for (ProcessThread_Task2 thread : threads) {
                    if (thread.isProcessed()) {
                        processedCount++;
                    }
                }
                if (processedCount == threads.size()) {
                    break;
                }
            }
        }
    }
}
