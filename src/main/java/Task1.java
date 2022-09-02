public class Task1 {
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Worker worker = new Worker();
        Thread clocker = new Thread(new Clock(worker));
        Thread messager = new Thread(new Messanger(worker));
        clocker.start();
        messager.start();
    }
}

class Worker {
    int seconds = 0;

    public void clock() {
        synchronized (Task1.lock) {
            System.out.println(seconds);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            seconds++;

            while (seconds % 5 == 0) {
                try {
                    System.out.println(seconds);
                    Task1.lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Task1.lock.notify();
        }
    }

    public void message() {
        synchronized (Task1.lock) {
            while (seconds % 5 != 0) {
                try {
                    Task1.lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            seconds++;
            System.out.println("5 seconds passed");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Task1.lock.notify();
        }
    }
}


class Clock implements Runnable {
    Worker worker;

    public Clock(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        while (true) {
            worker.clock();
        }
    }
}

class Messanger implements Runnable {
    Worker worker;

    public Messanger(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        while (true) {
            worker.message();
        }
    }
}