package concurrency_item;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * 让每个线程绑定自己的值
 * 可以将 ThreadLocal 类 形象的比喻成存放数据的盒子，盒子中可以存储每个线程的私有数据
 *
 * @author Pumpkin
 * @createTime 2023/3/9 20:42
 */
public class ThreadLocalTest implements Runnable {
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(threadLocalTest, "" + i);
            Thread.sleep(new Random().nextInt(1000));
            thread.start();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread Name = " + Thread.currentThread().getName() + " default Formatter = " + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name = "+ Thread.currentThread().getName()+" formatter = "+formatter.get().toPattern());
    }
    // 虽然 Thread-0 已经改变了 formatter 的值，但 Thread-1 默认格式化值与初始化值相同，其他线程也一样
    /*
    Thread Name = 0 default Formatter = yyyyMMdd HHmm
    Thread Name = 1 default Formatter = yyyyMMdd HHmm
    Thread Name = 0 formatter = y/M/d ah:mm
    Thread Name = 1 formatter = y/M/d ah:mm
    Thread Name = 2 default Formatter = yyyyMMdd HHmm
    Thread Name = 3 default Formatter = yyyyMMdd HHmm
    Thread Name = 2 formatter = y/M/d ah:mm
    Thread Name = 3 formatter = y/M/d ah:mm
    Thread Name = 4 default Formatter = yyyyMMdd HHmm
    Thread Name = 5 default Formatter = yyyyMMdd HHmm
    Thread Name = 5 formatter = y/M/d ah:mm
    Thread Name = 4 formatter = y/M/d ah:mm
    Thread Name = 6 default Formatter = yyyyMMdd HHmm
    Thread Name = 7 default Formatter = yyyyMMdd HHmm
    Thread Name = 6 formatter = y/M/d ah:mm
    Thread Name = 8 default Formatter = yyyyMMdd HHmm
    Thread Name = 9 default Formatter = yyyyMMdd HHmm
    Thread Name = 7 formatter = y/M/d ah:mm
    Thread Name = 9 formatter = y/M/d ah:mm
    Thread Name = 8 formatter = y/M/d ah:mm
     */
}
