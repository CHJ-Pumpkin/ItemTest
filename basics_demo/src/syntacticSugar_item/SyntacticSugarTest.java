package syntacticSugar_item;

import java.util.List;

/**
 * 语法糖
 *
 * @author Pumpkin
 * @createTime 2023/2/23 20:06
 */
public class SyntacticSugarTest {
    public static void main(String[] args) {
        LambdaDemo();
    }

    private static void LambdaDemo() {
        List<String> list = List.of("hello", "pumpkin", "pumpkin.top");
        List<String> stringList = list.stream().filter(s -> s.contains("hello")).toList();
        stringList.forEach(System.out::println);
    }
}
