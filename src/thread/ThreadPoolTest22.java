package thread;

public class ThreadPoolTest22 {

    public static void main(String[] args) {
// 创建一个可重用固定个数的线程池
        long l = System.currentTimeMillis();
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100000; i++) {
            // 打印正在执行的缓存线程信息
            System.out.println(Thread.currentThread().getName()
                    + "正在被执行");
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
