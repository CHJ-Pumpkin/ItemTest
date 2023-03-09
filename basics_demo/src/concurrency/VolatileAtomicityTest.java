package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Volatile 关键字能保证变量的可见性，但不能保证对变量的操作是原子性的
 *
 * @author Pumpkin
 * @createTime 2023/3/8 20:05
 */
public class VolatileAtomicityTest {
    public volatile static int inc = 0;
    public AtomicInteger increase = new AtomicInteger();

    /**
     * inc++ 是一个复合操作，包括三步:
     * 1. 读取 inc 的值
     * 2. 对 inc 加 1
     * 3. 将 inc 的值写回内存
     * 利用 synchronized 、Lock 、AtomicInteger 保证 inc++ 的原子性
     */
    Lock lock = new ReentrantLock();
    public void increaseForLock() {
        lock.lock();
        try {
            inc++;
        }finally {
            lock.unlock();
        }
    }

    /**
     * synchronized
     * 主要解决的是多个线程之间访问资源的同步性，可以保证被它修饰的方法或者代码块在任意时刻只能有一个线程执行
     * Java 6 之后引入了大量的优化
     * 如 自旋锁、锁消除、锁粗化、偏向锁、轻量级锁 等
     * 
     */
    public synchronized void increaseForSyn() {
        inc++;
    }
    public void increaseForAtom() {
        increase.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        VolatileAtomicityTest volatileAtomicityTest = new VolatileAtomicityTest();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(()->{
                for (int j = 0; j < 500; j++) {
                    volatileAtomicityTest.increaseForLock();
                }
            });
        }
        Thread.sleep(1500);
        System.out.println(inc);
        threadPool.shutdown();
    }
}
