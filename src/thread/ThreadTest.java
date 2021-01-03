package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {
    public static class CallerTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "hello,this is callable";
        }

        public static void main(String[] args) throws InterruptedException{
            FutureTask<String> futureTask = new FutureTask<>(new CallerTask());

            new Thread(futureTask).start();
            try {
                String result = futureTask.get();
                System.out.println(result);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

}
