package objects_item;

/**
 * 对象比较
 *
 * @author Pumpkin
 * @createTime 2023/2/23 18:14
 */
public class ObjectsTest {
    public static void main(String[] args) {
        ObjectToObject();
    }

    private static void ObjectToObject() {
        User aUser = new User("pumpkin", 24);
        User bUser = new User("pumpkin", 24);
        // 没重写 equals 和 hashCode 之前是 false 重写后是 true
        // 重写了 equals 必须重写 hashCode
        // 因为 hashCode 相等的两个对象不一定相等
        // 但是 相等的两个对象 hashCode 一定相等
        System.out.println(aUser.equals(bUser));
    }
}
