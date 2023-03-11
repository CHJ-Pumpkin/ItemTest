package stream_item;

import java.util.Arrays;
import java.util.List;

/**
 * 多线程 Stream
 * @author Pumpkin
 * @createTime 2023/3/12 0:15
 */
public class ParallelStreamTest {
    public static void main(String[] args) {
        test();
    }
    private static void test() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.parallelStream().forEach(num-> System.out.println(Thread.currentThread().getName()+" >> "+num));
    }
}
