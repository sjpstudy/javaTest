package thread;

public class ThreadTest3 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child threadOne over!");

            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    int count = 0;
                    for (; ; ) {
                        if (count == 100000) {
                            System.out.println(count);
                            break ;
                        }
                        count++;

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child threadTwo over!");
            }
        });


        long l = System.currentTimeMillis();
        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();
        System.out.println(System.currentTimeMillis()-l);
        System.out.println("mian is over");
    }
}
