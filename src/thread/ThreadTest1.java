package thread;

import thread.basic.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTest1 {

    /**
     * 定义线程池
     */
    public static ExecutorService executos = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        /**
         * 定义集合装结果集
         */
        List<User> totalList = new ArrayList<User>();

        /**
         * 定义 Future泛型的集合对象，装多线程的，返回信息
         */
        List<Future<List<User>>> list = new ArrayList<Future<List<User>>>();
        try {
            int pageIndex = 0;
            int maxPage = 6;
            for (pageIndex = 0; pageIndex < maxPage; pageIndex++) {
                /**
                 * executos.submit 返回线程未来结果
                 */
                Future<List<User>> future = executos.submit(new DemoThread(pageIndex));
                list.add(future);
            }

            for (Future<List<User>> dataFuture : list) {
                //  获得第一个任务的结果，如果调用get方法，当前线程会等待任务执行完毕后才往下执行
                totalList.addAll(dataFuture.get());
                //dataFuture.get()  这里会阻塞   有顺序的哦
            }

            //得到分页后结果总共集合
            for (int i = 0; i < totalList.size(); i++) {
                System.out.println(totalList.get(i).getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        executos.shutdownNow();
    }


    /**
     *  查询第1页
     查询第3页
     查询第5页
     查询第2页
     查询第4页
     查询第0页
     -----结果会顺序返回，不用你操心顺序问题啦
     anan0
     anan1
     anan2
     anan3
     anan4
     anan5
     */

}
