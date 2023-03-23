package concurrency_item;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子类
 *
 * @author Pumpkin
 * @createTime 2023/3/10 19:49
 */
public class AtomicTest {
    public static void main(String[] args) {
        AtomicIntegerDemo();
        // AtomicIntegerArrayDemo();
    }
    private static void AtomicIntegerDemo() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.getAndSet(5);
        System.out.println(atomicInteger.get());
        atomicInteger.getAndAdd(3);
        System.out.println(atomicInteger.get());
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.get());
        // 如果 expectedValue(预期) 的值 等于 内存值，则将内存对应的值改为 新值
        atomicInteger.compareAndSet(9, 100);
        System.out.println(atomicInteger.get());
    }

    private static void AtomicIntegerArrayDemo() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(nums);
        atomicIntegerArray.getAndSet(0, 5);
        System.out.println(atomicIntegerArray);
        atomicIntegerArray.getAndAdd(1, 3);
        System.out.println(atomicIntegerArray);
        atomicIntegerArray.getAndIncrement(2);
        System.out.println(atomicIntegerArray);
    }
}
