package thread;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest21 {

    public static void main(String[] args) {
// 创建一个可重用固定个数的线程池
        Map<String, Object> param = new HashMap<>();
        param.put("key", "value");
        System.out.println(param.get("key"));
        List list = new ArrayList<>();
        List list3 = new LinkedList();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {

                    try {
//结果依次输出，相当于顺序执行各个任务
                        System.out.println(Thread.currentThread().getName() + "正在被执行,打印的值是:" + index);
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }
}
