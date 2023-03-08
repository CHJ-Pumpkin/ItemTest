package genericity_item;

/**
 * 泛型
 *
 * @author Pumpkin
 * @createTime 2023/2/23 19:21
 */
public class GenericType {
    public <T> T generics(T name) {
        if (name instanceof String) {
            System.out.println("String");
            return name;
        } else if (name instanceof Integer) {
            System.out.println("Integer");
            return name;
        } else {
            System.out.println("既不是 String 也不是 Integer");
            return name;
        }
    }
}
