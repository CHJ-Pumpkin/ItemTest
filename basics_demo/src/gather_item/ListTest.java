package gather_item;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

/**
 * List 集合
 *
 * @author Pumpkin
 * @createTime 2023/2/24 15:12
 */
public class ListTest {
    public static void main(String[] args) {
        ArrayListDemo();
        VectorDemo();
        LinkedListDemo();
    }

    private static void ArrayListDemo() {
        // 底层使用 Object[] 存储
        // 不保证线程安全
        // 默认在末尾插入元素，时间复杂度为 O(1)
        // 如果在指定位置插入或删除元素，时间复杂度为 O(n-i)
        // 在 list 列表的结尾会预留一定的容量空间
        ArrayList<String> arrayList = new ArrayList<>();
        // 调用 add() 时，会检测数组大小是否充足，如不充足，则调用 grow() 方法进行扩容
        // 第一次调用 add() 方法(数组长度为 0 时)，设置默认长度为 10 与最小所需的长度较大的一个
        // 再次调用 add() 方法，最小所需的长度与数组当前长度加当前长度右移一位的值(之前的 1.5 倍)选择较大的一个
        arrayList.add("pumpkin");
        // 实现了 RandomAccess 接口，支持快速随机访问
        String res = arrayList.get(0);
        System.out.println(res);
    }

    private static void VectorDemo() {
        // 底层使用 Object[] 存储
        // 线程安全 synchronized
        // 调用构造方法的时候，默认使用 10 作为容量
        Vector<String> vector = new Vector<>();
        // 当前所需最小容量大于实际容量时会调用 grow() 方法
        // 在增量不指定的情况下，默认扩容到原来的 2 倍
        vector.add("pumpkin");
        String res = vector.get(0);
        System.out.println(res);
    }

    private static void LinkedListDemo() {
        // 底层使用 双向链表
        // 不保证线程安全
        // 每个元素都消耗相同空间并且消耗比 ArrayList 大（存放直接前驱和直接后继以及数据）
        LinkedList<String> linkedList = new LinkedList<>();
        // 头尾插入或者删除不受元素位置的影响，时间复杂度为 O(1)
        linkedList.add("pumpkin");
        linkedList.addFirst("pumpkinFirst");
        linkedList.addLast("pumpkinLast");
        linkedList.forEach(System.out::println);
        System.out.println("----------------");
        linkedList.removeFirst();
        linkedList.removeLast();
        linkedList.forEach(System.out::println);
        System.out.println("----------------");
        // 如果要在指定位置 i 插入或删除元素的话，时间复杂度为 O(n)
        linkedList.add(0, "pumpkin0");
        linkedList.add(1, "pumpkin1");
        linkedList.add(3, "pumpkin3");
        linkedList.forEach(System.out::println);
        System.out.println("----------------");
        // 支持存储 NULL 数据
        linkedList.add(null);
        linkedList.forEach(System.out::println);
        // 不支持高效的随机元素访问
        String res = linkedList.get(0);
        System.out.println(res);
    }
}
