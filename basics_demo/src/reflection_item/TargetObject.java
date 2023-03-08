package reflection_item;

/**
 * 测试对象
 * @author Pumpkin
 * @createTime 2023/1/22 20:57
 */
public class TargetObject {
    private final String value;

    public TargetObject() {
        value = "Pumpkin";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }

}
