package readFile_item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 读取文件并提取txt目录
 *
 * @author Pumpkin
 * @createTime 2023/2/1 16:42
 */
public class ReadFile {
    public static List<String> arrayList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入文件夹路径:");
        String filesPath = scanner.nextLine();

        findTxt(new File(filesPath));

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(i + "---" + arrayList.get(i));
        }

        int num = Integer.parseInt(scanner.nextLine());

        String filePath = filesPath + "\\" + arrayList.get(num);

        System.out.println(readFileContent(filePath));

    }

    /**
     * 读取文件内容
     *
     * @param filePath 路径
     * @return 目录
     */
    public static String readFileContent(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"))) {
            while ((line = bfr.readLine()) != null) {
                String lineReg = regex(line);
                if (lineReg != null) {
                    stringBuilder.append(lineReg).append("\n");
                }
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 正则表达式提取目录
     *
     * @param text
     * @return
     */
    public static String regex(String text) {
        Pattern pattern = Pattern.compile("^\\s*第\\d+章.*$");
        Matcher m = pattern.matcher(text);
        if (m.find()) {
            return m.group(0).trim();
        }
        return null;
    }

    /**
     * 扫描目录下的 txt 文件
     *
     * @param filesPath
     */
    public static void findTxt(File filesPath) {
        File[] files = filesPath.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    if (file.getName().endsWith(".txt")) {
                        // System.out.println(file.getName());
                        arrayList.add(file.getName());
                    }
                } else {
                    findTxt(file);
                }
            }
        }
    }
}
