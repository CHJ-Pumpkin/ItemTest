package concurrency;

/**
 * 双重校验锁实现对象单例(线程安全)
 * Volatile 关键字能保证变量的可见性，但不能保证对变量的操作是原子性的
 *
 * @author Pumpkin
 * @createTime 2023/3/8 14:02
 */
public class SingletonTest {
    private volatile static SingletonTest uniqueInstance;

    private SingletonTest(){

    }

    /**
     * JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2
     * 指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例
     * @return
     */
    public static SingletonTest getUniqueInstance() {
        // 先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            // 类对象加锁
            synchronized (SingletonTest.class) {
                if (uniqueInstance == null) {
                    // 分三步执行
                    // 1. 为 uniqueInstance 分配内存空间
                    // 2. 初始化 uniqueInstance
                    // 3. 将 uniqueInstance 指向分配的内存地址
                    uniqueInstance = new SingletonTest();
                }
            }
        }
        return uniqueInstance;
    }

    public static void main(String[] args) {
        System.out.println("getUniqueInstance() = " + getUniqueInstance());
    }
}
