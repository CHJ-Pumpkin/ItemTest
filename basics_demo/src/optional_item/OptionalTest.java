package optional_item;

import java.util.Optional;

/**
 * 防止 NPE
 * @author Pumpkin
 * @createTime 2023/3/12 0:25
 */
public class OptionalTest {
    public static void main(String[] args) {
        test();
    }
    private static void test() {
        Zoo zoo = new Zoo();
        Optional.ofNullable(zoo).map(Zoo::getDog).map(Dog::getAge).ifPresent(age-> System.out.println("age = " + age));
    }
}
