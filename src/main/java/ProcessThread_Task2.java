import java.util.concurrent.atomic.AtomicBoolean;

public class ProcessThread_Task2 extends Thread{
    private int number;
    private AtomicBoolean processed = new AtomicBoolean(true);
    private NumberProcessor_Task2 processor;

    public ProcessThread_Task2(NumberProcessor_Task2 processor) {
        this.processor = processor;
    }

    public void process(int number) {
        this.number = number;
        processed.set(false);
    }

    public boolean isProcessed() {
        return processed.get();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (processed.get()) {
                continue;
            }
            processor.process(number);

            processed.set(true);
        }
    }
}

