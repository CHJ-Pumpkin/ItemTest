package concurrency;

/**
 * 主要解决的是多个线程之间访问资源的同步性，可以保证被它修饰的方法或者代码块在任意时刻只能有一个线程执行
 * 可重入锁
 * volatile 和 synchronized 的区别:
 * volatile 能保证数据的可见性，但不能保证原子性；synchronized 两者都可以保证
 * volatile 主要用于解决变量在多个线程之间的可见性
 * synchronized 主要用于解决多个线程之间访问资源的同步性
 *
 * @author Pumpkin
 * @createTime 2023/3/9 18:45
 */
public class SynchronizedTest {
    /**
     * JDK 1.6 对锁的实现引入了大量的优化，如 偏向锁、锁消除、锁粗化、轻量级锁，自旋锁，适应性自旋锁 等技术来减少锁操作的开销
     * 锁主要存在四种状态，依次是: 无锁状态、偏向锁状态、轻量级锁状态、重量级锁状态，它们会随着竞争的激烈而逐渐升级
     * 注意: 锁可以升级不可降级
     * 为了提高获得锁和释放锁的效率
     */
    public static void main(String[] args) {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        synchronizedTest.method();
    }

    /**
     * 修饰方法 通过 ACC_SYNCHRONIZED 访问标志来声明
     */
    public synchronized void method() {
        // 修饰代码块 通过 monitor-enter，monitor-exit
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }
}
