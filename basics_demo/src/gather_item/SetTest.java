package gather_item;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * Set 集合
 *
 * @author Pumpkin
 * @createTime 2023/2/24 17:48
 */
public class SetTest {
    public static void main(String[] args) {
        HashSetDemo();
        LinkedHashSetDemo();
        TreeSetDemo();
    }

    private static void HashSetDemo() {
        // 无序 唯一
        // 基于 HashMap 实现
        // 底层采用 HashMap 来保存元素
        // 底层数据结构 哈希表
        // 线程不安全
        HashSet<String> hashSet = new HashSet<>();
        // 不保证插入和取出的顺序
        hashSet.add("pumpkin1");
        hashSet.add("pumpkin2");
        hashSet.add("pumpkin3");
        hashSet.forEach(System.out::println);
    }

    private static void LinkedHashSetDemo() {
        // 是 HashSet 的子类
        // 内部基于 LinkedHashMap
        // 底层数据结构 链表 + 哈希表
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        // 元素的插入和取出顺序满足 FIFO(First In First On)
        linkedHashSet.add("pumpkin3");
        linkedHashSet.add("pumpkin2");
        linkedHashSet.add("pumpkin1");
        linkedHashSet.forEach(System.out::println);
    }

    private static void TreeSetDemo() {
        // 有序 唯一
        // 红黑树
        // 底层数据结构 红黑树
        TreeSet<String> treeSet = new TreeSet<>();
        // 元素是有序的，排序的方式有自然排序和定制排序
        treeSet.add("pumpkin3");
        treeSet.add("pumpkin1");
        treeSet.add("pumpkin2");
        treeSet.forEach(System.out::println);
    }
}
