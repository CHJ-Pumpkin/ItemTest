package concurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 并发
 *
 * @author Pumpkin
 * @createTime 2023/2/25 18:35
 */
public class ConcurrentTest {
    // 一个进程有多个线程
    // 多个线程共享进程的 堆 和 方法区(1.8 之后的元空间) 资源
    // 堆是进程中最大的一块内存，主要用于存放新创建的对象
    // 方法区主要用于存放已被加载的类信息、常量、静态变量、即时编译器编译后的代码等数据
    // 每个线程有自己的 程序计数器、虚拟机栈和本地方法栈
    // 程序计数器: 私有主要是为了线程切换后能恢复到正确的执行位置
    // 虚拟机栈: java 方法在执行的同时会创建一个栈帧用于存储 局部变量表、操作数栈、常量池引用等信息
    // 本地方法栈: 为虚拟机使用到的 Native 方法服务
    public static void main(String[] args) {
        // 获取 java 线程管理 MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("["+threadInfo.getThreadId()+"]"+threadInfo.getThreadName());
        }
    }
}
