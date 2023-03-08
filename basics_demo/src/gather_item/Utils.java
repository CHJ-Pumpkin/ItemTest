package gather_item;

import java.util.Arrays;
import java.util.List;

/**
 * Utils 方法
 *
 * @author Pumpkin
 * @createTime 2023/2/25 18:08
 */
public class Utils {
    public static void main(String[] args) {
        List<String> list = List.of("pumpkin1", "female", "pumpkin.top");
        // 判断集合内部的元素是否为空，使用 isEmpty()
        System.out.println("list.isEmpty() = " + list.isEmpty());
        // 集合转数组
        String[] s = list.toArray(new String[0]);
        System.out.println(s.getClass());
        // 数组转集合
        List<String> asList = Arrays.asList(s);
        System.out.println(asList.getClass());
        // 数组转集合
        List<String> stringList = Arrays.stream(s).toList();
        System.out.println(stringList.getClass());
    }
}
