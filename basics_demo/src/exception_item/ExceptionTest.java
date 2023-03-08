package exception_item;

import java.io.*;

/**
 * 异常
 *
 * @author Pumpkin
 * @createTime 2023/2/23 15:45
 */
public class ExceptionTest {

    public static void main(String[] args) {
        try {
            String path = "basics_demo/src/exception_item/a.txt";
            readFile(path);
        } catch (MyException e) {
            System.out.println("自定义异常: " + e.getMessage());
            System.out.println("原始异常: " + e.getCause().getMessage());
        }
    }

    /**
     * IO异常
     *
     * @param filePath
     * @throws IOException
     */
    private static void readFile(String filePath) throws MyException {
        File file = new File(filePath);
        String result;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((result = bufferedReader.readLine()) != null) {
                System.out.println(result);
            }
        } catch (IOException e) {
            MyException myException = new MyException("找不到路径");
            // 包装异常
            myException.initCause(e);
            throw myException;
        }
    }
}
