package thread;

import thread.basic.User;

import javax.security.auth.callback.Callback;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DemoThread implements Callable<List<User>> {
    private int pageIndex;

    /**
     * 通过构造器，初始化
     * @param pageIndex
     */
    public DemoThread(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public List<User> call() throws Exception {
        //模拟每个线程执行耗时不一样
        if(pageIndex%2==0)
        {
            Thread.sleep(5000);
        }else
        {
            Thread.sleep(2000);
        }

        System.out.println("查询第" + pageIndex + "页");
        List<User> list = new ArrayList<User>();

        /**
         * 模拟查询的数据对象
         */
        User user=new User();
        user.setAge(""+pageIndex);
        user.setName("anan"+pageIndex);

        list.add(user);
        return list;
    }

}
