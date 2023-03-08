package xsoup_item;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

import java.io.IOException;

/**
 * xpath 爬虫
 *
 * @author Pumpkin
 * @createTime 2023/2/7 18:37
 */
public class XSoup_Xpath {
    private static Document writeDocument(String url) {
        try {
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.52")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void readHtml(String url, String xPathStr, String key) {
        Elements elements = Xsoup.compile(xPathStr).evaluate(writeDocument(url)).getElements();
        if (!elements.isEmpty()) {
            for (Element element : elements) {
                if (key == null) {
                    System.out.println(element.text());
                } else {
                    System.out.println(element.attr(key));
                }
            }
        } else {
            System.out.println("本页面是动态页面，无法爬虫！");
        }

    }
}