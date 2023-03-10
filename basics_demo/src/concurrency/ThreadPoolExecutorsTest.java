package concurrency;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 * 流程:
 * 1. 如果当前运行的线程数小于核心线程数，那么就会新建一个线程来执行任务
 * 2. 如果当前运行的线程数等于或大于核心线程数，但是小于最大线程数，那么就把该任务放入到任务队列里等待执行
 * 3. 如果向任务队列投放任务失败(任务队列满了)，但是当前运行的线程数小于最大线程数，就新建一个线程来执行任务
 * 4. 如果当前运行的线程数已经等同于最大线程数了，新建线程将会使当前运行的线程超出最大线程数，那么当前任务会被拒绝，饱和策略会调用 RejectedExecutionHandler.rejectedExecution() 方法
 *
 * @author Pumpkin
 * @createTime 2023/3/9 21:25
 */
public class ThreadPoolExecutorsTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutorsTest threadPoolExecutorsTest = new ThreadPoolExecutorsTest();
        threadPoolExecutorsTest.ThreadPoolExecutorDemo();
        // 返回可用处理器的Java虚拟机的数量
        System.out.println("Runtime.getRuntime().availableProcessors() = " + Runtime.getRuntime().availableProcessors());
    }

    private void ThreadPoolExecutorDemo() throws InterruptedException {
        // 线程池的核心线程数量
        int corePoolSize = 2;
        // 线程池的最大线程数
        int maximumPoolSize = 5;
        // 任务队列，用来存储等待执行任务的队列
        BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>(2);

        // 当线程数大于核心线程数时，多余的空闲线程存活的最长时间
        long keepAliveTime = 5L;
        // 时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        AtomicInteger atomicInteger = new AtomicInteger(1);
        // 线程工厂，用来创建线程，一般默认即可
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                // 自定义线程池名称
                return new Thread(r, "test-" + atomicInteger.getAndIncrement());
            }
        };
        // 拒绝策略，当提交的任务过多而不能及时处理时，我们可以定制策略来处理任务
        RejectedExecutionHandler handler = new IgnorePolicy();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );

        printThreadPoolStatus(threadPoolExecutor);

        final int taskCount = 7;
        CountDownLatch countDownLatch = new CountDownLatch(taskCount);

        for (int i = 0; i < 10; i++) {
            // Future 异步调用
            // threadPoolExecutor.submit(() -> {
            threadPoolExecutor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(new Random().nextInt(3000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        System.out.println("任务全部完成:" + threadPoolExecutor.getCompletedTaskCount());
        threadPoolExecutor.shutdown();
    }

    static class IgnorePolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            doLog(r);
        }

        private void doLog(Runnable r) {
            System.err.println(r.toString() + " rejected");
        }
    }

    /**
     * 监测线程池运行状态
     *
     * @param threadPoolExecutor
     */
    private static void printThreadPoolStatus(ThreadPoolExecutor threadPoolExecutor) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "print-images/thread-pool-status");
            }
        });
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            System.out.println("=========================");
            System.out.println(Thread.currentThread().getName());
            System.out.println("ThreadPool Size: [" + threadPoolExecutor.getPoolSize() + "]");
            System.out.println("Active Threads: " + threadPoolExecutor.getActiveCount());
            System.out.println("Number of Tasks : " + threadPoolExecutor.getCompletedTaskCount());
            System.out.println("Number of Tasks in Queue: " + threadPoolExecutor.getQueue().size());
            System.out.println("=========================");
        }, 0, 1, TimeUnit.SECONDS);
    }
}
