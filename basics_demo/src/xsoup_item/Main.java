package xsoup_item;

/**
 * @author Pumpkin
 * @createTime 2023/2/8 16:57
 */
public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();
        XSoup_Xpath.readHtml(
                "XXX",
                "//*[@id=\"category_grid\"]/table/tbody/tr/td/div/ul/li/a",
                "title"
        );
        System.out.println("\n耗时: "+(System.nanoTime()-start)/1_000_000_000.0+"秒");
    }
}
