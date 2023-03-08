package bigDecimal_item;

import java.math.BigDecimal;

/**
 * @author Pumpkin
 * @createTime 2023/2/23 19:42
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        float a = 2.0f - 1.9f;
        float b = 1.8f - 1.7f;
        System.out.println(a);
        System.out.println(b);

        BigDecimal c = new BigDecimal("2.0");
        BigDecimal d = new BigDecimal("1.9");
        // 相减 0.1
        System.out.println(c.subtract(d));
        // 相加 3.9
        System.out.println(c.add(d));
    }
}
