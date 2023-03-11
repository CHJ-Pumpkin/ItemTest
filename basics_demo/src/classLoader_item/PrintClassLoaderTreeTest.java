package classLoader_item;

/**
 * 类加载器
 * 双亲委派模型
 * 自定义类加载器 如果要打破双亲委派就重写 loadClass() 方法
 * 自定义类加载器 如果不打破双亲委派就重写 findClass() 方法
 * 自底向上查找判断类是否被加载
 * 自顶向下尝试加载类
 * @author Pumpkin
 * @createTime 2023/3/11 22:30
 */
public class PrintClassLoaderTreeTest {
    public static void main(String[] args) {
        ClassLoader classLoader = PrintClassLoaderTreeTest.class.getClassLoader();
        StringBuilder stringBuilder = new StringBuilder("|--");
        boolean needContinue = true;
        while (needContinue) {
            System.out.println(stringBuilder.toString() + classLoader);
            if (classLoader == null) {
                needContinue = false;
            } else {
                classLoader = classLoader.getParent();
                stringBuilder.insert(0, "\t");
            }
        }
    }
}
