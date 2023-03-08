package exception_item;

/**
 * 自定义异常
 * @author Pumpkin
 * @createTime 2023/2/23 16:36
 */
public class MyException extends Exception{
    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
