package dateTime_item;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Pumpkin
 * @createTime 2023/3/12 0:50
 */
public class FormatTest {
    public static void main(String[] args) {
        test5();
    }
    private static void test() {
        // format yyyy-MM-dd
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate format : "+localDate);

        LocalTime localTime = LocalTime.now();
        System.out.println("localTime format : " + localTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime format : " + localDateTime);

        LocalDate withYear = LocalDate.now().withYear(2066);
        System.out.println("withYear = " + withYear);

        // format HH:mm:ss
        LocalTime withNano = LocalTime.now().withNano(0);
        System.out.println("withNano = " + withNano);

        // format yyyy-MM-dd HH:mm:ss
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTime.format(dateTimeFormatter);
        System.out.println("format = " + format);
    }

    /**
     * 字符串转日期格式
     */
    private static void test2() {
        LocalDate date = LocalDate.of(2066, 5, 20);
        System.out.println("date = " + date);
    }

    /**
     * 日期计算
     */
    private static void test3() {
        // 一周后的日期
        LocalDate localDate = LocalDate.now();

        // 方法一
        LocalDate after = localDate.plus(1, ChronoUnit.WEEKS);
        System.out.println("一周后的日期(方法一) = " + after);

        // 方法二
        LocalDate after2 = localDate.plusWeeks(1);
        System.out.println("一周后的日期(方法二) = " + after2);

        LocalDate date1 = LocalDate.parse("2066-05-20");
        LocalDate date2 = LocalDate.parse("2066-10-20");
        Period period = Period.between(date1, date2);
        // 打印结果是 “date1 到 date2 相隔: 0年5月0日”
        // 这里period.getDays()得到的天是抛去年月以外的天数，并不是总天数
        System.out.println("date1 到 date2 相隔: "
                + period.getYears() + "年"
                + period.getMonths() + "月"
                + period.getDays() + "日");

        // 如果要获取纯粹的总天数应该用下面的方法
        long days = date2.toEpochDay() - date1.toEpochDay();
        System.out.println(date1 + "和" + date2 + "相差" + days + "天");
    }

    /**
     * 获取特定日期
     */
    private static void test4() {
        LocalDate localDate = LocalDate.now();
        // 获取当前月第一天
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("firstDayOfMonth = " + firstDayOfMonth);
        // 获取当前月最后一天
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("lastDayOfMonth = " + lastDayOfMonth);
        // 获取下一天
        LocalDate plusDays = lastDayOfMonth.plusDays(1);
        System.out.println("plusDays = " + plusDays);
        // 获取当年最后一天
        LocalDate lastDayOfYear = localDate.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("lastDayOfYear = " + lastDayOfYear);
        // 获取指定月份的最后一个周日
        LocalDate lastInMonthOfDayOfWeek = LocalDate.parse("2066-05-20").with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println("lastInMonthOfDayOfWeek = " + lastInMonthOfDayOfWeek);
    }

    private static void test5() {
        // 当前时区时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("当前时区时间: "+zonedDateTime);

        // 东京时间
        ZoneId zoneId = ZoneId.of(ZoneId.SHORT_IDS.get("JST"));
        ZonedDateTime withZoneSameInstant = zonedDateTime.withZoneSameInstant(zoneId);
        System.out.println("东京时间: "+withZoneSameInstant);

        // ZonedDateTime 转 LocalDateTime
        LocalDateTime localDateTime = withZoneSameInstant.toLocalDateTime();
        System.out.println("东京时间转当地时间: "+localDateTime);

        // LocalDateTime 转 ZonedDateTime
        ZonedDateTime atZone = localDateTime.atZone(ZoneId.systemDefault());
        System.out.println("本地时区时间: " + atZone);

        /*
        当前时区时间: 2023-03-12T01:28:40.273769300+08:00[Asia/Shanghai]
        东京时间: 2023-03-12T02:28:40.273769300+09:00[Asia/Tokyo]
        东京时间转当地时间: 2023-03-12T02:28:40.273769300
        本地时区时间: 2023-03-12T02:28:40.273769300+08:00[Asia/Shanghai]
         */
    }
}
