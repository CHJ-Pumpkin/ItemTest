package execCommand_item;

import java.io.*;

/**
 * 调用 cmd 并执行
 * @author Pumpkin
 * @createTime 2023/1/31 23:24
 */
public class ExecCommand {
    public static void main(String[] args) {
        try {
            execCommandAndGetOutPut();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void execCommandAndGetOutPut() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("cmd.exe /c netstat -ano | findStr 3306");
        // 输出结果，必须写在 waitFor之前
        String outStr = getStreamStr(process.getInputStream());
        // 错误结果，必须写在 waitFor之前
        String errStr = getStreamStr(process.getErrorStream());
        int exitValue = process.waitFor(); // 退出值 0 为正常，其他为异常
        System.out.println("exitValue = \n" + exitValue);
        System.out.println("outStr = \n" + outStr);
        System.out.println("errStr = \n" + errStr);
        process.destroy();
    }

    public static String getStreamStr(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        br.close();
        return sb.toString();
    }
}
