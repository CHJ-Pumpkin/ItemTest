package stream_item;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream流
 * 方法参数都是函数式接口类型
 * 一个 Stream 只能操作一次，操作完就关闭了，继续使用这个 Stream 会报错
 * Stream 不保存数据，不改变数据源
 *
 * @author Pumpkin
 * @createTime 2023/3/11 23:32
 */
public class StreamTest {
    public static void main(String[] args) {
        test();
    }

    /**
     * 注意 一个 stream 只能操作一次，不能断开，否则会报错
     * 报错 java.lang.IllegalStateException: stream has already been operated upon or closed
     */
    private static void test() {
        List<String> stringList = Arrays.asList("abc", "def", "ghi", "jkl", "mno", "abc", "ghi");
        stringList.forEach(System.out::println);

        System.out.println("====================");
        // 返回符合条件的 stream
        Stream<String> stringStream = stringList.stream().filter("abc"::equals);
        long count = stringStream.count();
        System.out.println("count = " + count);

        System.out.println("====================");
        // limit 获取到 1 个元素的 stream
        Stream<String> limit = stringList.stream().limit(1);
        // String[]::new 等于 size -> new String[size]
        String[] strings = limit.toArray(String[]::new);
        for (String string : strings) {
            System.out.println(string);
        }

        System.out.println("====================");
        // 对每个元素进行操作 返回新流
        Stream<String> map = stringList.stream().map(s -> s + 22);
        map.forEach(System.out::println);

        System.out.println("====================");
        // 排序并打印
        stringList.stream().sorted().forEach(System.out::println);

        System.out.println("====================");
        // 把 abc 放入容器
        List<String> collect = stringList.stream().filter("abc"::equals).toList();
        collect.forEach(System.out::println);

        System.out.println("====================");
        // 把 list 转为 string，各元素之间用 ， 号隔开
        String mergedString = stringList.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println("mergedString = " + mergedString);

        System.out.println("====================");
        List<Integer> numberList = Arrays.asList(1, 2, 5, 4, 3);
        numberList.forEach(System.out::println);

        System.out.println("====================");
        IntSummaryStatistics intSummaryStatistics = numberList.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数: " + intSummaryStatistics.getMax());
        System.out.println("列表中最小的数: " + intSummaryStatistics.getMin());
        System.out.println("平均数: " + intSummaryStatistics.getAverage());
        System.out.println("所有数之和: " + intSummaryStatistics.getSum());

        System.out.println("====================");
        List<String> stringList2 = Arrays.asList("xyz", "jqx");
        // concat 合并流
        Stream<String> concat = Stream.concat(stringList.stream(), stringList2.stream());
        concat.forEach(System.out::println);
    }
}
