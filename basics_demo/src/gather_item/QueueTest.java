package gather_item;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

/**
 * Queue 集合
 *
 * @author Pumpkin
 * @createTime 2023/2/24 18:19
 */
public class QueueTest {
    public static void main(String[] args) {
        // PriorityQueueDemo();
        ArrayDeque();
    }

    private static void PriorityQueueDemo() {
        // Object[] 数组来实现二叉堆
        // 元素出队顺序与优先级相关，即总是优先级最高的元素先出队
        // 数据结构 二叉堆
        // 底层使用 可变长的数组 来存储数据
        // n个节点，树的高度为 logN，时间复杂度为 O(logN)
        // 建堆的时间复杂度为 O(n)
        // 线程不安全
        // 不支持存储 NULL 数据
        // 默认小顶堆
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        // 通过堆的上浮和下沉，实现了在 O(logN) 的时间复杂度内插入元素和删除堆顶元素
        // 上浮: 新元素放在数组末尾，与父节点比较，如果不满足堆的性质，则交换父子节点，直到满足堆的性质
        // 下沉: 取出堆顶元素，将数组最后一个元素放到数组第一个元素的位置，与子节点比较，如果不满足堆的性质，则交换父子节点，大顶堆交换大的那个子节点，小顶堆交换小的那个子节点
        priorityQueue.add("pumpkin1");
        priorityQueue.add("pumpkin3");
        priorityQueue.add("pumpkin2");
        priorityQueue.forEach(System.out::println);
    }

    private static void ArrayDeque() {
        // Object[] + 双指针
        // 不支持存储 NULL 数据
        // 插入时可能存在扩容过程，不过均摊后的插入操作时间复杂度依然为 O(1)
        // 从性能上说，实现队列要比 LinkedList 更好
        // 可以用于实现栈 pop push
        ArrayDeque<String> arrayDeque = new ArrayDeque<>(1);
        // 插入队首
        arrayDeque.addFirst("pumpkin1");
        arrayDeque.offerFirst("pumpkin2");
        // 插入队尾
        arrayDeque.add("pumpkin");
        // 插入队尾
        arrayDeque.addLast("pumpkin3");
        arrayDeque.offerLast("pumpkin4");
        // 删除队首
        arrayDeque.removeFirst();
        arrayDeque.pollFirst();
        // 删除队尾
        arrayDeque.removeLast();
        arrayDeque.pollLast();
        // 查询队首
        System.out.println(arrayDeque.getFirst());
        System.out.println(arrayDeque.peekFirst());
        // 查询队尾
        System.out.println(arrayDeque.getLast());
        System.out.println(arrayDeque.peekLast());
        arrayDeque.push("pumpkin1");
        System.out.println(arrayDeque.pop());
    }
}
