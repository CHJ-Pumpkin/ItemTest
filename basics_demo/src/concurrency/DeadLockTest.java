package concurrency;

/**
 * 死锁
 * @author Pumpkin
 * @createTime 2023/3/8 12:41
 */
public class DeadLockTest {
    // 资源 1
    private final static Object resource1 = new Object();
    // 资源 2
    private final static Object resource2 = new Object();
    public static void main(String[] args) {
        DeadLockDemo();
        BreakTheLoopWaitCondition();
    }

    /**
     * 产生死锁的四个必要条件
     * 互斥条件: 该资源任意一个时刻只由一个线程占用
     * 请求与保持条件: 一个线程因请求资源而阻塞时，对已获得的资源保持不放
     * 不剥夺条件: 线程已获得的资源在未使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源
     * 循环等待条件: 若干线程之间形成一种头尾相接的循环等待资源关系
     */
    private static void DeadLockDemo() {
        Thread(resource2,resource1);
    }

    /**
     * 如何预防和避免线程死锁
     * 如何预防死锁？ 破坏死锁的产生的必要条件即可:
     * 1. 破坏请求与保持条件: 一次性申请所有的资源
     * 2. 破坏不剥夺条件: 占用部分资源的线程进一步申请其他资源时，如果申请不到，可以主动释放它占有的资源
     * 3. 破坏循环等待条件: 靠按序申请资源来预防。按某一顺序申请资源，释放资源则反序释放
     */
    private static void BreakTheLoopWaitCondition() {
        Thread(resource1,resource2);
    }


    private static void Thread(Object resource1,Object resource2){
        new Thread(() -> {
            synchronized (DeadLockTest.resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    // sleep() 与 wait() 方法对比
                    // 共同点: 两者都可以暂停线程的执行
                    // sleep() 方法没有释放锁，wait() 方法释放了锁
                    // wait() 通常被用于线程间交互/通信，线程不会自动苏醒，需要别的线程调用同一对象上的 notify() 或者 notifyAll() 方法
                    // sleep() 通常被用于暂停执行，线程会自动苏醒，或者也可以使用 wait(long timeout) 超时后线程会自动苏醒。
                    // sleep() 是 Thread 类的静态本地方法（不涉及到对象）， wait() 则是 Object 类的本地方法（操作对应的对象）
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (DeadLockTest.resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 1").start(); // 调用 start() 方法方可启动线程并使线程进入就绪状态，直接执行 run() 方法的话不会以多线程的方式执行

        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        }, "线程 2").start();
    }

}

