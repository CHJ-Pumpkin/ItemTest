package gather_item;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map 集合
 *
 * @author Pumpkin
 * @createTime 2023/2/25 15:08
 */
public class MapTest {
    public static void main(String[] args) {
        // HashMapDemo();
        // LinkedHashMapDemo();
        // HashtableDemo();
        // TreeMapDemo();
        ConcurrentHashMapDemo();
    }

    private static void HashMapDemo() {
        // JDK 1.8 之前 由 数组+链表 组成
        // JDK 1.8 之后 由 数组+链表/红黑树 组成
        // 当链表长度大于阈值(默认为 8 )，并且当前数组长度小于 64 时，会选择先进行数组扩容，而不是转换为红黑树
        // 线程不安全
        // 可以储存 null 的 key 和 value，但 null 作为键只能有一个，null 作为值可以有多个
        // 默认初始化大小为 16，之后每次扩容都为原来的 2 倍
        // 如果指定了初始化容量，会将其扩容到 2 的幂次方大小
        // (n-1)&hash == hash%n
        // 假设 n = 16，hash = 33
        // n = 00010000，(n-1) = 00001111，hash = 00100001
        // 00001111 & 00100001 = 00000001 = 1
        // 33%16 = 2余1
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "pumpkin1");
        hashMap.put(2, "pumpkin2");
        hashMap.put(3, "pumpkin3");
        hashMap.put(4, "pumpkin4");
        hashMap.put(5, "pumpkin5");
        hashMap.put(6, "pumpkin6");

        // 迭代器 EntrySet
        Iterator<Map.Entry<Integer, String>> entryIterator = hashMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, String> entry = entryIterator.next();
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }

        // 迭代器 KeySet
        Iterator<Integer> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println(key + "---" + hashMap.get(key));
        }

        // ForEach EntrySet
        for (Map.Entry<Integer, String> integerStringEntry : hashMap.entrySet()) {
            System.out.println(integerStringEntry.getKey() + "---" + integerStringEntry.getValue());
        }

        // ForEach KeySet
        for (Integer key : hashMap.keySet()) {
            System.out.println(key + "---" + hashMap.get(key));
        }

        // Lambda
        hashMap.forEach((key, value) -> {
            System.out.println(key + "---" + value);
        });

        // Streams API 单线程
        hashMap.entrySet().stream().forEach((entry) -> {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        });

        // Streams API 多线程
        hashMap.entrySet().parallelStream().forEach((entry) -> {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        });
    }

    private static void LinkedHashMapDemo() {
        // 由 数组+链表/红黑树 组成
        // 增加了一条 双向链表，可以保持键值对的插入顺序与访问顺序
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(3, "pumpkin3");
        linkedHashMap.put(1, "pumpkin1");
        linkedHashMap.put(2, "pumpkin2");
        linkedHashMap.forEach((key, value) -> {
            System.out.println(key + "---" + value);
        });
    }

    private static void HashtableDemo() {
        // 由 数组+链表 组成
        // 线程安全(synchronized)
        // 不允许有 null 键和 null 值，否则会抛出 NullPointerException
        // 默认初始容量为 11，每次扩容为原来的 2n+1
        // 基本已经淘汰
        Hashtable<Integer, String> hashtable = new Hashtable<>();
    }

    private static void TreeMapDemo() {
        // 由 红黑树 组成
        // 实现 NavigableMap 接口让 TreeMap 有了对集合内元素的搜索的能力
        // 实现 SortedMap 接口让 TreeMap 有了对集合中的元素根据键排序的能力，默认是按 key 的升序排序，不过也可以指定排序的比较器。
        TreeMap<Integer, String> treeMap = new TreeMap<>((k1, k2) -> {
            int num = k1 - k2;
            return Integer.compare(num, 0);
        });
        treeMap.put(2, "pumpkin2");
        treeMap.put(1, "pumpkin1");
        treeMap.put(3, "pumpkin3");
        treeMap.forEach((key, value) -> {
            System.out.println(key + "---" + value);
        });
    }

    private static void ConcurrentHashMapDemo() {
        // JDK 1.7 底层采用 分段的数组+链表 组成
        // JDK 1.8 底层采用 数组+链表/红黑树 组成
        // JDK 1.7 对整个桶数组进行了分割分段(segment,分段锁)，每一把锁只锁容器其中一部分数据，多线程访问容器里不同数据段的，就不会存在锁竞争，提高并发访问率。
        // JDK 1.8 直接用 Node 数组+链表/红黑树，并发控制使用 synchronized 和 CAS 来操作，synchronized 只锁定当前链表或红黑二叉树的首节点，这样只要 hash 不冲突，就不会产生并发。
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();

    }
}
