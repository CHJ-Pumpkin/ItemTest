package string_item;

import java.util.Objects;

/**
 * 字符串比较
 *
 * @author Pumpkin
 * @createTime 2023/2/23 17:23
 */
public class StringTest {
    public static void main(String[] args) {
        StringToString();
    }

    private static void StringItem() {
        // private final byte[] value;
        // String 不可变 ， 因为 final 使得 value 不可以被第二次赋值，private 使得 value 不能被外界访问
        String str1 = "he";
        String str2 = "llo";
        String str3 = "world";
        // java 8 之前 StringBuilder 的 append() 方法连接
        // java 9 开始 动态调用
        String str4 = str1 + str2 + str3;
    }

    private static void StringToString() {
        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        String d = new String("abc");
        Integer e = Integer.valueOf(500);
        Integer f = Integer.valueOf(500);
        // == 比较内存地址
        System.out.println(a == b);
        System.out.println(a == c);
        // intern() 返回常量池引用
        System.out.println(a == c.intern());
        // String.equals 比较内容
        System.out.println(a.equals(c));
        // Integer.equals 比较值
        System.out.println(Objects.equals(e, f));
        System.out.println(e.equals(f));
        // 比较内存地址
        System.out.println(e == f);
        // 将其中一个 Integer 转化为 int ，比较值
        System.out.println(e == f.intValue());
    }

}
