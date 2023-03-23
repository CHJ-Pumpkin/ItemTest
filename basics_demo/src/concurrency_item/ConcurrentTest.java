package concurrency_item;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 并发: 两个及两个以上的作业在同一时间段内执行
 * 并行: 两个及两个以上的作业在同一时刻执行
 * 同步: 发出一个调用之后，在没有得到结果之前，该调用就不可以返回，一直等待
 * 异步: 调用在发出之后，不用等待返回结果，该调用直接返回
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
            // [1]main 线程，程序入口
            // [2]Reference Handler 清除 Reference 线程
            // [3]Finalizer 调用对象 Finalizer 方法的线程
            // [4]Signal Dispatcher 分发处理给 JVM 信号的线程
            // [5]Attach Listener 添加事件
            // [13]Common-Cleaner
            // [14]Monitor Ctrl-Break
            // [15]Notification Thread
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }
    }
}

// 线程的声明周期和状态
// NEW: 初始状态，线程被创建出来但没有被调用 start()
// RUNNABLE: (READY-RUNNING)就绪(运行状态)，线程被调用了 start() 等待运行的状态
// BLOCKED: 阻塞状态，需要等待锁释放
// WAITING: 等待状态，表示该线程需要等待其他线程做出一些特定动作（通知或中断）
// TIME_WAITING: 超时等待状态，可以在指定的时间后自动返回而不是像 WAITING 那样一直等待
// TERMINATED: 终止状态，表示该线程已经运行完毕