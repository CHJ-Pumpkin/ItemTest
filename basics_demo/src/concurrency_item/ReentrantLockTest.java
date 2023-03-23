package concurrency_item;

/**
 * 可重入且独占式的锁
 * 增加了 轮询、超时、中断、公平锁(FairSync)、非公平锁(NonFairSync)
 * 公平锁(FairSync): 锁被释放之后，先申请的线程先得到锁
 * 非公平锁(NonFairSync): 锁被释放之后，后申请的线程可能会先获取到锁，是随机或者按照其他优先级排序的
 * Synchronized 是 JVM 实现的
 * ReentrantLock 是 API 实现的
 * @author Pumpkin
 * @createTime 2023/3/9 19:16
 */
public class ReentrantLockTest {
    /**
     * 可中断锁和不可中断锁
     * ReentrantLock 是 可中断锁: 不需要一直等到获取锁之后 才能进行其他逻辑处理
     * Synchronized 是 不可中断锁: 只能等到拿到锁以后才能进行其他的逻辑处理
     * 共享锁和独占锁的区别:
     * 共享锁: 一把锁可以被多个线程同时获得
     * 独占锁: 一把锁只能被一个线程获得
     * 线程持有读锁的情况下，该线程不能取得写锁
     * 线程持有写锁的情况下，该线程可以继续获取读锁(只有写锁没有被当前线程占用的情况下才会获取失败)
     * 写锁可以降级为读锁，但是读锁不可以升级为写锁，升级会引起线程的争夺，因为写锁是独占锁
     */
    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        // 由于 synchronized 是可重入锁
        // 同一个线程在调用 method1() 时可以直接获取当前对象的锁，执行 method2() 的时候可以再次获取这个对象的锁，不会产生死锁问题
        reentrantLockTest.method1();
    }

    public synchronized void method1() {
        System.out.println("方法 1");
        method2();
    }

    public synchronized void method2() {
        System.out.println("方法 2");
    }
}
