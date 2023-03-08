package reflection_item;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射
 * @author Pumpkin
 * @createTime 2023/1/22 20:56
 */
public class Reflection_Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        /*
          获取 targetObject 类的 Class 对象并且创建 TargetObject 类实例
         */
        Class<?> targetClass = Class.forName("reflection_item.TargetObject");
        TargetObject targetObject = (TargetObject) targetClass.getDeclaredConstructor().newInstance();

        /*
          获取 TargetObject 类中定义的所有方法
         */
        for (Method method : targetClass.getDeclaredMethods()) {
            System.out.println(method.getName());
        }

        /*
          获取指定方法并调用
         */
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject, "Pumpkin");

        /*
          获取指定参数并且对参数进行修改
         */
        Field field = targetClass.getDeclaredField("value");
        field.setAccessible(true);
        field.set(targetObject, "Pumpkins");

        /*
          调用private方法
         */
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);

    }
}
