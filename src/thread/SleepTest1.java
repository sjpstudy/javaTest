package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepTest1 {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException{
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("child thradA is in sleep");
                    Thread.sleep(1000);
                    System.out.println("child threadOne is in awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("child threadB is in sleep");
                    Thread.sleep(1000);
                    System.out.println("child threadB is in awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        threadOne.start();
        threadB.start();
    }
}
