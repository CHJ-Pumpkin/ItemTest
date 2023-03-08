package genericity_item;

/**
 * 泛型测试
 *
 * @author Pumpkin
 * @createTime 2023/2/23 19:21
 */
public class GenericTest {
    public static void main(String[] args) {
        GenericType genericType = new GenericType();
        String string = genericType.generics("pumpkin");
        System.out.println(string);
        Integer integer = genericType.generics(123);
        System.out.println(integer);
    }

}
